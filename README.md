# ProVU
Advanced Programming Course : final project

##  cool features in this project
### FXML model
`gui.helper.ModelControl` and `gui.helper.ModelControlCollection` help us to use FXML model with custom data

example :

model/messageItemInMessageBoard.fxml
```xml
<HBox spacing="8" styleClass="card-item" HBox.hgrow="ALWAYS" xmlns:fx="http://javafx.com/fxml/1" >
    <Label text="{title}"  styleClass="em, em-message" />
    <JFXButton text="مشاهده" />
    <Pane HBox.hgrow="ALWAYS" />
    <Label text="{commentCount}" minWidth="50" styleClass="em, em-speech" />
    <Label text="{sendTime}" minWidth="140" styleClass="em, em-date" />
</HBox>
```

MessageBoardController.java
```java
@FXML private VBox messageSubjectHolder; // defined in MessageBoard.fxml (not model!)
...
ModelControlCollection posts = new ModelControlCollection(messageSubjectHolder, "model/messageItemInMessageBoard.fxml") {
    @Override
    public void onButtonClick (NameValue data) {
        selectPost(data.getInt("id"));
    }
};
...
posts.add(mypost);
// {title} and other vars in fxml replaced with `mypost` data and result will add to `messageSubjectHolder`
```
#### Notice
1. `mypost` in last line have `toNameValue()` method that get needed data for generate model (you can see it in `Post.java`)
2. as you can see here we can also handle button click actions inside generated models, also we can get `buttonId`. useful when we have more than 1 button. (look at `gui.CourseBoardController` )
3. instead of `text="{title}"` something like `text="{title} lorem {user.name}"` is allowed !


### IF in FXML
one other cool stuff is that we can use If inside FXML !

example:

myCourseInCourseBoard.fxml
```xml
<HBox spacing="8" styleClass="card-item" HBox.hgrow="ALWAYS" xmlns:fx="http://javafx.com/fxml/1" >
    <Label text="{name}" minWidth="80" styleClass="em, em-books, txt-med" />
    <If x="{section}, {page}" is="myCourse, CourseBoard" >
        <If x="{user.type}" is="Teacher" >
            <JFXButton id="btn_showCourse" text="مشاهده" styleClass="green, txt-white, txt-med" />
        </If>
        <If x="{user.type}" is="Student" >
            <JFXButton id="btn_delete" text="حذف" styleClass="red, txt-med" />
        </If>
    </If>
    <If x="{page}, {section}, {user.type}" is="CourseBoard, allCourse, Student" >
        <JFXButton text="اضافه" styleClass="green, txt-med" />
    </If>
    ...
</HBox>
```
#### Notice
1. If in FXML need parse with `ModelControl.java` or `ModelControlCollection.java`
2. we have not else / elseif right now !

## license
feel free for everything,
but i have not  any responsibility!

