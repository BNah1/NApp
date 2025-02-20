package com.example.napp.constant;

import com.example.napp.R;
import com.example.napp.data.model.Comment;
import com.example.napp.data.model.Post;
import com.example.napp.data.model.UserProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Constant {
    static public int testAvatar = R.drawable.ic_launcher_background;
    static public int testImage = R.drawable.buster;


    // tao UserProfile hien tai khi da dang nhap
    static public UserProfile testUser = new UserProfile("bonah","B@gmail.com","111",R.drawable.xenon_art,"b-21-11");


    // tao list post
    static public List<Post> testListPost= new ArrayList<>();
    public static List<Post> getTestListPost() {
        testListPost.add(post1);
        testListPost.add(post1);
        testListPost.add(post1);
        return testListPost;
    }

    //tao list comment

    static List<Comment> testListComment = new ArrayList<>();
    public static List<Comment> getTestListComment() {
        testListComment.add(comment);
        testListComment.add(comment);
        return testListComment;
    }

    static List<String> stringList = Arrays.asList(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4"
    );
    static String testLongString = "la link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo quala link anh nhung tam thoi bo qua";

    static Date time = new Date();
    static Post post1 = new Post("1","bonah1","la link anh nhung tam thoi bo qua",time,stringList,getTestListComment(),testLongString);
    static Comment comment = new Comment("bonah1","bonah1-11","bonah1-11-112",time,stringList);





}
