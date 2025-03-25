
import dal.BillingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import model.Billing;
import model.BillingCombo;
import utils.EmailSender;

@WebServlet("/updatePaymentStatus")
public class PaymentStatusServlet extends HttpServlet {

    private BillingDAO billingDAO = new BillingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy Billing ID và action từ tham số yêu cầu
        String billingID = request.getParameter("billingID");
        String action = request.getParameter("action");

        if (billingID == null || billingID.isEmpty()) {
            response.getWriter().write("Billing ID is required.");
            return;
        }

        if (action == null || action.isEmpty()) {
            response.getWriter().write("Action is required.");
            return;
        }

        String newStatus = null;

        // Xử lý action và cập nhật trạng thái
        if ("approve".equalsIgnoreCase(action)) {
            newStatus = "Completed"; // Cập nhật trạng thái thành "Completed" khi approve
        } else if ("decline".equalsIgnoreCase(action)) {
            newStatus = "Declined"; // Cập nhật trạng thái thành "Declined" khi decline
        } else {
            response.getWriter().write("Invalid action.");
            return;
        }

        try {
            // Cập nhật trạng thái thanh toán trong cơ sở dữ liệu
            boolean isUpdated = billingDAO.updatePaymentStatus(billingID, newStatus);
            if (isUpdated) {
                // Lấy thông tin của hóa đơn từ database
                Billing billing = billingDAO.getBillingByID(billingID);

                // Log và kiểm tra dữ liệu trả về từ database
                if (billing == null) {
                    response.getWriter().write("Billing not found after status update.");
                    return; // Nếu không tìm thấy hóa đơn, dừng lại tại đây
                }

                // Nếu có thông tin hóa đơn, tiếp tục xử lý
                List<String> seatNames = billingDAO.getSeatsByBillingID(billingID);
                List<BillingCombo> combos = billingDAO.getCombosByBillingID(billingID);
                int roomID = billingDAO.getRoomIDByShowtimeID(billing.getShowtimeID());
                String movieName = billingDAO.getMovieNameByShowtimeID(billing.getShowtimeID());
                BigDecimal totalAmount = billing.getTotalAmount();
                String userEmail = billingDAO.getEmailByUserID(billing.getUserID());

                // Gửi email thông báo cho khách hàng
                sendPaymentEmail(userEmail, seatNames, combos, totalAmount, roomID, movieName);

                // Redirect về trang danh sách billing
                response.sendRedirect("billing"); // Quay lại danh sách Billing
            } else {
                response.getWriter().write("Failed to update payment status.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred: " + e.getMessage());
        }
    }

    private void sendPaymentEmail(String toEmail, List<String> seatNames, List<BillingCombo> combos, BigDecimal totalAmount, int roomID, String movieName) {
        String subject = "Payment Confirmation - Movie Ticket Booking";
        StringBuilder content = new StringBuilder();

        content.append("<h3>Payment Confirmation</h3>")
               .append("<p>Your payment has been successfully completed.</p>")
               .append("<p><strong>Movie:</strong> ").append(movieName).append("</p>")
               .append("<p><strong>Room:</strong> ").append(roomID).append("</p>")
               .append("<p><strong>Total Amount:</strong> $").append(totalAmount).append("</p>")
               .append("<h4>Seats:</h4><ul>");

        for (String seat : seatNames) {
            content.append("<li>").append(seat).append("</li>");
        }
        content.append("</ul>");

        content.append("<h4>Combos:</h4><ul>");
        for (BillingCombo combo : combos) {
            content.append("<li>Combo ID: ").append(combo.getComboID()).append(" - Quantity: ").append(combo.getQuantity()).append("</li>");
        }
        content.append("</ul>");

        EmailSender.sendEmail(toEmail, subject, content.toString());
    }
}
