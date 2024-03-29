package com.study.studyspring.domain;

import java.time.OffsetDateTime;

public class Board {

    private int idx;
    private String title;

    private String content;
    private String date;
    private int view;
    private int member_idx;

    private String time;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getMember_idx() {
        return member_idx;
    }

    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }
}
