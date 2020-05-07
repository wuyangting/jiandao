package com.example.myapplication.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchBean {
    @Id
   private String keyWord;
   private Long time;
@Generated(hash = 1667305898)
public SearchBean(String keyWord, Long time) {
    this.keyWord = keyWord;
    this.time = time;
}
@Generated(hash = 562045751)
public SearchBean() {
}
public String getKeyWord() {
    return this.keyWord;
}
public void setKeyWord(String keyWord) {
    this.keyWord = keyWord;
}
public Long getTime() {
    return this.time;
}
public void setTime(Long time) {
    this.time = time;
}
}
