package com.teamlab.fujiiyosuke.todoApp.form;

import com.teamlab.fujiiyosuke.todoApp.constraints.Deadline;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * TodoのForm用Beans
 * @author fujii
 */
@Data
public class TodoForm {
    @NotBlank
    @Size(max = 30)
    private String name;

    @Deadline
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
}
