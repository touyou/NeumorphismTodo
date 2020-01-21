package com.teamlab.fujiiyosuke.Todo.Form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TodoForm {
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
}
