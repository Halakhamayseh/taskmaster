package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    //select all from database
    @Query("SELECT * FROM taskclass")
    List<TaskClass>getAll();

    //select spesfic task by id
    @Query("SELECT * FROM taskclass WHERE taskId IN (:taskClassIds)")
    List<TaskClass>loadAllByIds(int[]taskClassIds);

    //select spesfic task by userName  // you can use if went the user enter name and went to find by username
//    @Query("SELECT * FROM taskclass WHERE first_name LIKE :first AND"+"last_name like :last LIMIT 1")
//    TaskClass findBYName(String first,String last);

    //insert
    @Insert
    void insertAll(TaskClass allTasks);

    //delete
    @Delete
    void delete(TaskClass task);
}
