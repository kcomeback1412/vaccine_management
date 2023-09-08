document.getElementById("cancel-button").addEventListener("click", function () {
    history.back();
});


function editNews() {
    var ids = []; // Mảng lưu trữ các newsId đã được chọn

    // Lặp qua tất cả các checkbox và kiểm tra xem chúng có được chọn hay không
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            var newsId = checkbox.getAttribute('data-news-id');
            ids.push(newsId);
        }
    });


    if (ids.length > 0) {
        var url = '/update_news/' + ids.join(',');
        window.location.href = url; // Chuyển hướng đến trang cập nhật với các newsId đã chọn
    } else {

        alert('Please select at least one news to update.');
    }
}


function deleteNews() {
    var selectedNews = document.querySelectorAll('input[name="newsIds"]:checked');

    if (selectedNews.length > 0) {
        // Tạo một mảng chứa các ID của tin tức được chọn
        var selectedIds = Array.from(selectedNews).map(function (checkbox) {
            return checkbox.getAttribute('data-news-id');
        });

        var params = new URLSearchParams();
        selectedIds.forEach(function (id) {
            params.append('newsIds', id);
        });

         if(confirm("Do you want to delete the record?")){
         }else {
             window.location.href = '/news_list';
         }
// Sử dụng fetch để gửi yêu cầu POST với các tham số URL
        fetch('/delete_news?' + params.toString(), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .then(function (response) {
                if (response.ok) {

                    window.location.href = '/news_list'; // Chuyển hướng về danh sách tin tức
                }
            })

    } else {
        alert("No data delete!");
    }
}


function validateForm() {
    var newsType = document.getElementById("new_type").value;
    if (newsType === "" || newsType === "-- Select NewsType --") {
        alert("Please select News Type.");
        return false; // không cho gửi khi chưa chọn News Type
    }
    return true; // Cho phép gửi khi chọn News Type
}
