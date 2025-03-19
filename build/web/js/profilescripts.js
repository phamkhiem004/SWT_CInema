document.addEventListener("DOMContentLoaded", function () {
    // Lấy các form
    const profileForm = document.getElementById("profile-form");
    const passwordForm = document.getElementById("password-form");

    // Hàm hiển thị lỗi
    function showError(input, message) {
        const errorMessage = input.nextElementSibling;
        errorMessage.innerText = message;
        errorMessage.style.color = "red";
    }

    // Hàm xóa lỗi
    function clearError(input) {
        const errorMessage = input.nextElementSibling;
        errorMessage.innerText = "";
    }

    // Hàm kiểm tra input có bị trống không
    function validateInput(input) {
        if (!input.value.trim()) {
            showError(input, "Trường này không được để trống");
            return false;
        }
        clearError(input);
        return true;
    }

    // Kiểm tra email
    function validateEmail(input) {
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(input.value)) {
            showError(input, "Email không hợp lệ");
            return false;
        }
        clearError(input);
        return true;
    }

    // Kiểm tra số điện thoại
    function validatePhone(input) {
        const phonePattern = /^0[0-9]{9}$/;
        if (!phonePattern.test(input.value)) {
            showError(input, "Số điện thoại phải có 10 số và bắt đầu bằng 0");
            return false;
        }
        clearError(input);
        return true;
    }

    // Kiểm tra ngày sinh (tối thiểu 12 tuổi)
    function validateDateOfBirth(input) {
        const dob = new Date(input.value);
        const today = new Date();
        const minAge = 12; // Số tuổi tối thiểu
        const maxAge = 100; // Giới hạn tuổi tối đa

        if (isNaN(dob.getTime())) { 
            showError(input, "Ngày sinh không hợp lệ");
            return false;
        }

        const age = today.getFullYear() - dob.getFullYear();
        const monthDiff = today.getMonth() - dob.getMonth();
        const dayDiff = today.getDate() - dob.getDate();

        if (age < minAge || (age === minAge && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)))) {
            showError(input, `Bạn phải từ ${minAge} tuổi trở lên`);
            return false;
        }

        if (age > maxAge) {
            showError(input, `Tuổi tối đa là ${maxAge} tuổi`);
            return false;
        }

        clearError(input);
        return true;
    }

    // Kiểm tra mật khẩu có đủ mạnh không
    function validatePassword(input) {
        const passwordPattern = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
        if (!passwordPattern.test(input.value)) {
            showError(input, "Mật khẩu phải có ít nhất 6 ký tự, 1 chữ hoa, 1 số, 1 ký tự đặc biệt");
            return false;
        }
        clearError(input);
        return true;
    }

    // Kiểm tra xác nhận mật khẩu
    function validatePasswordMatch(password, confirmPassword) {
        if (password.value !== confirmPassword.value) {
            showError(confirmPassword, "Mật khẩu không trùng khớp");
            return false;
        }
        clearError(confirmPassword);
        return true;
    }

    // Validate Form Thông tin cá nhân
    profileForm.addEventListener("submit", function (e) {
        e.preventDefault();
        let isValid = true;

        isValid &= validateInput(document.getElementById("full-name"));
        isValid &= validateInput(document.getElementById("email")) && validateEmail(document.getElementById("email"));
        isValid &= validateInput(document.getElementById("phone")) && validatePhone(document.getElementById("phone"));
        isValid &= validateInput(document.getElementById("dob")) && validateDateOfBirth(document.getElementById("dob"));
        isValid &= validateInput(document.getElementById("gender"));
        isValid &= validateInput(document.getElementById("address"));

        if (isValid) {
            profileForm.submit();
        }
    });

    // Validate Form Thay đổi mật khẩu
    passwordForm.addEventListener("submit", function (e) {
        e.preventDefault();
        let isValid = true;

        isValid &= validateInput(document.getElementById("current-password"));
        isValid &= validateInput(document.getElementById("new-password")) && validatePassword(document.getElementById("new-password"));
        isValid &= validateInput(document.getElementById("confirm-password")) &&
                  validatePasswordMatch(document.getElementById("new-password"), document.getElementById("confirm-password"));

        if (isValid) {
            passwordForm.submit();
        }
    });
});

function filterTransactions() {
    const startDate = document.getElementById("start-date").value;
    const endDate = document.getElementById("end-date").value;
    const transactions = document.querySelectorAll("#transaction-body tr");

    transactions.forEach(row => {
        const rowDate = row.getAttribute("data-date");  // Lấy ngày giao dịch từ thẻ <tr>
        const rowYear = rowDate.split("-")[0]; // Lấy năm từ ngày giao dịch

        // Chuyển đổi sang dạng số để dễ so sánh
        const rowTimestamp = new Date(rowDate).getTime();
        const startTimestamp = startDate ? new Date(startDate).getTime() : null;
        const endTimestamp = endDate ? new Date(endDate).getTime() : null;

        let showRow = true;

        // Lọc theo năm nếu có chọn
        if (filterYear !== "all" && rowYear !== filterYear) {
            showRow = false;
        }

        // Lọc theo khoảng thời gian (nếu có)
        if (startTimestamp && rowTimestamp < startTimestamp) {
            showRow = false;
        }
        if (endTimestamp && rowTimestamp > endTimestamp) {
            showRow = false;
        }

        row.style.display = showRow ? "" : "none";
    });
}


