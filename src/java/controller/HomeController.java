//package controller;
//
//import dal.MovieDAO;
//import model.Account;
//import model.Movie;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@WebServlet(name = "HomeController", urlPatterns = {"/home"})
//public class HomeController extends HttpServlet {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//    }
//
//    @Override
//   protected void doGet(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//        
//        HttpSession session = request.getSession();
//        Account acc = (Account) session.getAttribute("account");
//
//        // Kiểm tra xem người dùng đã đăng nhập chưa
//        if (acc == null) {
//            response.sendRedirect("login");
//        } else {
//            // Get the page and search parameters from the request
//            String pageParam = request.getParameter("page");
//            String searchParam = request.getParameter("search");
//            int page = 1; // Default to the first page
//            int pageSize = 6; // Set the desired page size
//            String categoryParam = request.getParameter("category");
//            String sortOption = request.getParameter("sort");
//
//            if (pageParam != null && !pageParam.isEmpty()) {
//                // Parse the page parameter to an integer
//                page = Integer.parseInt(pageParam);
//            }
//            if (sortOption == null) {
//                sortOption = "";
//            }
//
//            MovieDAO movieDAO = new MovieDAO();
//            List<Movie> movieList = null;
//
//            try {
//                // Lấy danh sách phim đang chiếu từ cơ sở dữ liệu
//                movieList = movieDAO.getNowShowingMovies();
//            } catch (SQLException e) {
//                // Xử lý lỗi trong trường hợp có lỗi khi truy vấn cơ sở dữ liệu
//                e.printStackTrace(); // In ra thông tin lỗi để kiểm tra
//                request.setAttribute("errorMessage", "An error occurred while retrieving movie data.");
//                request.getRequestDispatcher("error.jsp").forward(request, response); // Chuyển hướng sang trang lỗi
//                return; // Dừng việc xử lý tiếp
//            }
//
//            // Tiếp tục xử lý nếu không có lỗi
//            request.setAttribute("searchParam", searchParam);
//            request.setAttribute("listM", movieList);
//            request.setAttribute("totalPages", movieList.size() % pageSize == 0 ? (movieList.size() / pageSize) : (movieList.size() / pageSize + 1));
//            request.setAttribute("currentPage", page);
//            request.getRequestDispatcher("home.jsp").forward(request, response);
//        }
//    }
//    @Override
//    public String getServletInfo() {
//        return "HomeController Servlet for managing home page.";
//    }
//}
package controller;

import dal.MovieDAO;
import model.Account;
import model.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

        // Lấy các tham số từ request
        String pageParam = request.getParameter("page");
        String searchParam = request.getParameter("search");
        String categoryParam = request.getParameter("category");
        String sortOption = request.getParameter("sort");

        // Gán giá trị mặc định nếu không có tham số từ request
        int page = 1;
        int pageSize = 6;
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        if (sortOption == null) {
            sortOption = "";
        }

        // Khởi tạo các đối tượng DAO
        MovieDAO movieDAO = new MovieDAO();
        // Lấy danh sách thể loại
        // Lấy danh sách phim với phân trang và tìm kiếm
        List<Movie> movies;
        try {
            movies = movieDAO.getAllMovies();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Cập nhật các thuộc tính request để truyền cho JSP

        // Cập nhật các thuộc tính request để truyền cho JSP
        request.setAttribute("acc", acc);

        // Chuyển hướng tới trang home.jsp để hiển thị danh sách phim
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "HomeController Servlet for managing home page.";
    }
}
