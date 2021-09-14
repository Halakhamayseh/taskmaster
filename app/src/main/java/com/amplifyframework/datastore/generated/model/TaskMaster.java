package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the TaskMaster type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TaskMasters")
@Index(name = "byTask", fields = {"teamId","taskTitle"})
public final class TaskMaster implements Model {
  public static final QueryField ID = field("TaskMaster", "id");
  public static final QueryField TASK_TITLE = field("TaskMaster", "taskTitle");
  public static final QueryField TASK_BODY = field("TaskMaster", "taskBody");
  public static final QueryField TASK_STATE = field("TaskMaster", "taskState");
  public static final QueryField IMG = field("TaskMaster", "img");
  public static final QueryField TEAMS = field("TaskMaster", "teamId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String taskTitle;
  private final @ModelField(targetType="String", isRequired = true) String taskBody;
  private final @ModelField(targetType="String") String taskState;
  private final @ModelField(targetType="String") String img;
  private final @ModelField(targetType="Team", isRequired = true) @BelongsTo(targetName = "teamId", type = Team.class) Team teams;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTaskTitle() {
      return taskTitle;
  }
  
  public String getTaskBody() {
      return taskBody;
  }
  
  public String getTaskState() {
      return taskState;
  }
  
  public String getImg() {
      return img;
  }
  
  public Team getTeams() {
      return teams;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private TaskMaster(String id, String taskTitle, String taskBody, String taskState, String img, Team teams) {
    this.id = id;
    this.taskTitle = taskTitle;
    this.taskBody = taskBody;
    this.taskState = taskState;
    this.img = img;
    this.teams = teams;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TaskMaster taskMaster = (TaskMaster) obj;
      return ObjectsCompat.equals(getId(), taskMaster.getId()) &&
              ObjectsCompat.equals(getTaskTitle(), taskMaster.getTaskTitle()) &&
              ObjectsCompat.equals(getTaskBody(), taskMaster.getTaskBody()) &&
              ObjectsCompat.equals(getTaskState(), taskMaster.getTaskState()) &&
              ObjectsCompat.equals(getImg(), taskMaster.getImg()) &&
              ObjectsCompat.equals(getTeams(), taskMaster.getTeams()) &&
              ObjectsCompat.equals(getCreatedAt(), taskMaster.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), taskMaster.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTaskTitle())
      .append(getTaskBody())
      .append(getTaskState())
      .append(getImg())
      .append(getTeams())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TaskMaster {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("taskTitle=" + String.valueOf(getTaskTitle()) + ", ")
      .append("taskBody=" + String.valueOf(getTaskBody()) + ", ")
      .append("taskState=" + String.valueOf(getTaskState()) + ", ")
      .append("img=" + String.valueOf(getImg()) + ", ")
      .append("teams=" + String.valueOf(getTeams()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TaskTitleStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static TaskMaster justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new TaskMaster(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      taskTitle,
      taskBody,
      taskState,
      img,
      teams);
  }
  public interface TaskTitleStep {
    TaskBodyStep taskTitle(String taskTitle);
  }
  

  public interface TaskBodyStep {
    TeamsStep taskBody(String taskBody);
  }
  

  public interface TeamsStep {
    BuildStep teams(Team teams);
  }
  

  public interface BuildStep {
    TaskMaster build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep taskState(String taskState);
    BuildStep img(String img);
  }
  

  public static class Builder implements TaskTitleStep, TaskBodyStep, TeamsStep, BuildStep {
    private String id;
    private String taskTitle;
    private String taskBody;
    private Team teams;
    private String taskState;
    private String img;
    @Override
     public TaskMaster build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new TaskMaster(
          id,
          taskTitle,
          taskBody,
          taskState,
          img,
          teams);
    }
    
    @Override
     public TaskBodyStep taskTitle(String taskTitle) {
        Objects.requireNonNull(taskTitle);
        this.taskTitle = taskTitle;
        return this;
    }
    
    @Override
     public TeamsStep taskBody(String taskBody) {
        Objects.requireNonNull(taskBody);
        this.taskBody = taskBody;
        return this;
    }
    
    @Override
     public BuildStep teams(Team teams) {
        Objects.requireNonNull(teams);
        this.teams = teams;
        return this;
    }
    
    @Override
     public BuildStep taskState(String taskState) {
        this.taskState = taskState;
        return this;
    }
    
    @Override
     public BuildStep img(String img) {
        this.img = img;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String taskTitle, String taskBody, String taskState, String img, Team teams) {
      super.id(id);
      super.taskTitle(taskTitle)
        .taskBody(taskBody)
        .teams(teams)
        .taskState(taskState)
        .img(img);
    }
    
    @Override
     public CopyOfBuilder taskTitle(String taskTitle) {
      return (CopyOfBuilder) super.taskTitle(taskTitle);
    }
    
    @Override
     public CopyOfBuilder taskBody(String taskBody) {
      return (CopyOfBuilder) super.taskBody(taskBody);
    }
    
    @Override
     public CopyOfBuilder teams(Team teams) {
      return (CopyOfBuilder) super.teams(teams);
    }
    
    @Override
     public CopyOfBuilder taskState(String taskState) {
      return (CopyOfBuilder) super.taskState(taskState);
    }
    
    @Override
     public CopyOfBuilder img(String img) {
      return (CopyOfBuilder) super.img(img);
    }
  }
  
}
