$(document).ready(function () {
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/post/1"
    }).then(function(data) {
        $("#post-creator-identity").text(data.postCreator.userInformation.firstname + " " + data.postCreator.userInformation.lastname);
        $("#post-elapsed-time").text(data.elapsedCreationTimeMessage + " ago.");
        $("#post-content").text(data.content);
        $("#post-creator-image").attr("src", data.postCreator.userInformation.profilePicturePath);
        $("#post-image").attr("src", data.imagePath);
        $("#posts").fadeIn("slow");
    })
});