$(document).ready(function () {
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/posts"
    }).then(function(data) {
        data.forEach(function (post) {
            let newPost = $('div[name="post[0]"]').eq(0).clone();

            newPost.attr("name", newPost.attr("name").replace('[0]', `[${post.id}]`));
            newPost.find('span[name^="post[0]"], div[name^="post[0]"]').each(function () {
                $(this).attr("name", $(this).attr("name").replace('[0]', `[${post.id}]`));
            });
            $("#posts").append(newPost);

            post.reactionCounters.forEach(function (counter) {
                let newReaction = $(`div[name="post[${post.id}].reaction[0]"]`).eq(0).clone();

                newReaction.attr("name", newReaction.attr("name").replace('[0]', `[${counter.id}]`));
                $(`div[name="post[${post.id}].reactions"]`).append(newReaction);

                $(`div[name="post[${post.id}].reaction[${counter.reaction.id}]"] span`).text(counter.count);
                $(`div[name="post[${post.id}].reaction[${counter.reaction.id}]"] img`).attr("src", counter.reaction.imagePath);
            })
            $(`div[name="post[${post.id}].reaction[0]"]`).remove();

            $(`div[name="post[${post.id}].image"] img`).attr("src", post.imagePath);
            $(`div[name="post[${post.id}].creator"] img`).attr("src", post.postCreator.userInformation.profilePicturePath);
            $(`div[name="post[${post.id}].creator"] span`).text(post.postCreator.userInformation.firstname + " " + post.postCreator.userInformation.lastname);
            $(`span[name="post[${post.id}].elapsed-time"]`).text(post.elapsedCreationTimeMessage + " ago.");
            $(`span[name="post[${post.id}].content"]`).text(post.content);
        })
        $('div[name="post[0]"]').remove();
        $('#posts').fadeIn("slow");
    })
});