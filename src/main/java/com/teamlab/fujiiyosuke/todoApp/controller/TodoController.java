package com.teamlab.fujiiyosuke.todoApp.controller;

import com.teamlab.fujiiyosuke.todoApp.form.SearchForm;
import com.teamlab.fujiiyosuke.todoApp.form.TodoForm;
import com.teamlab.fujiiyosuke.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

/**
 * TodoのControllerクラス
 * @author fujii
 */
@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    /**
     * Index page
     * @param adminPass Adminパスワードをパラメタに渡すことで全削除ボタンが現れる
     * @param form 空のフォームデータもしくはエラー時の元の入力値が入る
     * @param mav ModelAndView
     * @return 設定済のModelAndView
     */
    @GetMapping("/")
    public ModelAndView index(@RequestParam(name = "admin", required = false) String adminPass, @ModelAttribute("todoForm")TodoForm form, ModelAndView mav) {
        mav.setViewName("top");
        mav.addObject("formatter", new SimpleDateFormat("yyyy年MM月dd日"));
        mav.addObject("list", todoService.findAllOrderByCreateDate());
        mav.addObject("debug", todoService.isDebugMode(adminPass));
        return mav;
    }

    /**
     * Add request
     * @param form バリデーションを含めたフォームデータ
     * @param result バリデーション結果
     * @param mav ModelAndView
     * @return 加工済のModelAndView
     */
    @PostMapping("/add")
    @Transactional(readOnly = false)
    public ModelAndView add(@ModelAttribute("todoForm")@Validated TodoForm form, BindingResult result, ModelAndView mav) {
        todoService.addValidate(result, form.getName());
        if (result.hasErrors()) {
            return index("", form, mav);
        }
        todoService.create(form.getName(), form.getDeadline());
        return new ModelAndView("redirect:/");
    }

    /**
     * Doneの切り替え
     * @param id 切り替えるTodoのID
     * @param mav ModelAndView
     * @return Indexへのリダイレクト
     */
    @PostMapping("/done/{id}")
    @Transactional(readOnly = false)
    public ModelAndView switchStatus(@PathVariable Long id, ModelAndView mav) {
        todoService.updateDone(id);
        return new ModelAndView("redirect:/");
    }

    /**
     * Edit View
     * @param id path id
     * @param form 空のフォーム
     * @param mav ModelAndView
     * @return 設定済みのModelAndView
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, @ModelAttribute("todoForm")TodoForm form, ModelAndView mav) {
        mav.setViewName("edit");
        todoService.setEditForm(id, form);
        mav.addObject("id", id);
        return mav;
    }

    /**
     * Update request
     * @param id path id
     * @param form フォームのデータ
     * @param result バリデーションの結果
     * @param mav ModelAndView
     * @return 設定済みのModelAndView
     */
    @PostMapping("/edit/{id}")
    @Transactional(readOnly = false)
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("todoFom")@Validated TodoForm form, BindingResult result, ModelAndView mav) {
        todoService.editValidate(result, form.getName(), id);
        if (result.hasErrors()) {
            mav.setViewName("edit");
            mav.addObject("todoForm", form);
            mav.addObject("id", id);
            mav.addObject("validationError", result.getFieldErrors());
            return mav;
        }
        todoService.update(id, form.getName(), form.getDeadline());
        return new ModelAndView("redirect:/");
    }

    /**
     * Delete todo
     * @param id todo id
     * @param mav model and view
     * @return redirect index
     */
    @PostMapping("/delete/{id}")
    @Transactional(readOnly = false)
    public ModelAndView delete(@PathVariable Long id, ModelAndView mav) {
        todoService.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    /**
     * Delete all
     * @param mav model and view
     * @return redirect index
     */
    @PostMapping("/clear")
    @Transactional(readOnly = false)
    public ModelAndView deleteAll(ModelAndView mav) {
        todoService.deleteAll();
        return new ModelAndView("redirect:/");
    }

    /**
     * Search page
     * @param word 検索パラメータ
     * @param mav ModelAndView
     * @return 設定済のModelAndView
     */
    @GetMapping("/search")
    public ModelAndView search(@RequestParam(name = "word", required = false)String word, @ModelAttribute("searchForm")SearchForm form, ModelAndView mav) {
        mav.setViewName("search");
        mav.addObject("formatter", new SimpleDateFormat("yyyy年MM月dd日"));
        mav.addObject("list", todoService.findByPartOfName(word));
        mav.addObject("word", word);
        return mav;
    }

    /**
     * Doneの切り替え
     * @param id 切り替えるTodoのID
     * @param mav ModelAndView
     * @return Searchへのリダイレクト
     */
    @PostMapping("/search/done/{id}")
    @Transactional(readOnly = false)
    public ModelAndView switchStatusInSearch(@RequestParam(name = "word")String word, @PathVariable Long id, ModelAndView mav) {
        todoService.updateDone(id);
        return new ModelAndView("redirect:/search?word=" + word);
    }
}
