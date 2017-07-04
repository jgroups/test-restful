package me.lbing.rest.controller;

import me.lbing.rest.dto.ResponseResult;
import me.lbing.rest.exception.ResourceNotFoundException;
import me.lbing.rest.model.Employee;
import me.lbing.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Spring 4.0 引入了 @RestController 注解，
 * 在控制器是用 @RestController 代替 @Controller 的话，Spring 将会为该控制器的所有处理方法应用消息转换功能。
 * 我们不必在每个方法都添加 @ResponseBody 注解了。
 */
@Controller
@RequestMapping("/employs")
public class EmployeeController {
    @Autowired
    private EmployeeService empService;

    /**
     * 正常情况下，当处理方法返回 Java 对象时，这个对象会放在模型中并在视图中渲染使用。
     * 但是，如果使用了消息转换功能的话，我们需要告诉 Spring 跳过正常的模型/视图流程，并使用消息转换器。
     * 实现这种方式最简单的方式是在控制器的方法上添加 @ResponseBody 注解。
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public List<Employee> employs(Integer offset, Integer limit) {
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 20 : limit;
        return empService.queryEmployList(offset,limit);
    }

    /**
     * 使用 @RequestBody 注解可以告知 Spring 查找一个消息转换器，将来自客户端的资源表述转换为对象。
     * @param employee
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public int saveEmploy(@RequestBody Employee employee) {
        return empService.save(employee);
    }

    /**
     * 可以利用 ResponseEntity 给客户端返回状态码、设置响应头信息等
     * 
     * 另外，ResponseEntity 还包含有 @ResponseBody 的语义，上面示例中并没有使用 @ResponseBody 注解，
     * 但是 ResponseEntity 的负载部分同样可以渲染到响应体中。
     * @param id
     * @return
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
//    public ResponseEntity<Employee> employById(@PathVariable long id) {
//        HttpStatus status = null;
//        Employee Employee = empService.selectById(id);
//        if (Employee != null) {
//            status = HttpStatus.OK;
//        } else {
//            status = HttpStatus.NOT_FOUND;
//        }
//        return new ResponseEntity<Employee>(Employee, status);
//    }

    @Autowired
    private EmployeeService empService;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    public ResponseResult<Employee> employById(@PathVariable long id) {
        ResponseResult<Employee> result = new ResponseResult<Employee>();
        HttpStatus status = null;
        Employee Employee = empService.selectById(id);
        if (Employee == null) {
            throw new ResourceNotFoundException(String.valueOf(id));
        }
        result.setCode(String.valueOf(HttpStatus.OK));
        result.setData(Employee);
        return result;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseResult<Object> handlerException(ResourceNotFoundException e) {
        ResponseResult<Object> result = new ResponseResult<Object>();
        result.setCode(String.valueOf(HttpStatus.NOT_FOUND));
        result.setDesc(e.getMessage());
        return result;
    }
}
