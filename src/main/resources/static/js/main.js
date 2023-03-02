$(document).ready(function () {
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/posts"
    }).then(function(data) {
        data.forEach(function (post) {
            let newPost = $('div[name="post[0]"]').eq(0).clone();

            newPost.attr("name", newPost.attr("name").replace('[0]', '[' + post.id + ']'));
            newPost.find('span').each(function () {
                $(this).attr("name", $(this).attr("name").replace('[0]', '[' + post.id + ']'));
            });
            $("#posts").append(newPost);

            $('span[name="post[' + post.id + '].creator-identity"]').text(post.postCreator.userInformation.firstname + " " + post.postCreator.userInformation.lastname);
            $('span[name="post[' + post.id + '].elapsed-time"]').text(post.elapsedCreationTimeMessage + " ago.");
            $('span[name="post[' + post.id + '].content"]').text(post.content);
        })
        $('div[name="post[0]"]').remove();
        $('#posts').fadeIn("slow");
    })
});