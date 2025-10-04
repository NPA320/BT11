$(document).ready(function () {
  const path = window.location.pathname;

  // --- Chỉ chạy khối này khi đang ở trang profile ---
  if (path === "/user/profile" || path === "/profile.html") {
    $.ajax({
      type: "GET",
      url: "/users/me",
      dataType: "json",
      contentType: "application/json; charset=utf-8",
      beforeSend: function (xhr) {
        if (localStorage.token) {
          xhr.setRequestHeader("Authorization", "Bearer " + localStorage.token);
        }
      },
      success: function (data) {
        $("#profile").html(data.fullName ? data.fullName : data.email);
        if (data.images) $("#images").attr("src", data.images);
      },
      error: function (e) {
        // Nếu hết hạn token/ chưa login, quay về /login (không alert ở trang login)
        if (e.status === 401 || e.status === 403) {
          window.location.href = "/login";
        }
      }
    });

    $("#LogoutBtn").click(function () {
      localStorage.clear();
      window.location.href = "/login";
    });
  }

  // --- Chỉ bind login ở trang login ---
  if (path === "/login" || path === "/login.html") {
    $("#LoginBtn").click(function () {
      var email = $("#email").val();
      var password = $("#password").val();

      $.ajax({
        type: "POST",
        url: "/auth/login",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({ email, password }),
        success: function (data) {
          localStorage.token = data.token;
          window.location.href = "/user/profile"; // đúng mapping view
        },
        error: function () {
          alert("❌ Login Failed");
        }
      });
    });
  }
});
