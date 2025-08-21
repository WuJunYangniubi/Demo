package org.wujunyang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Data
@AllArgsConstructor
public class PageResult<T> {
    private long total;
    private List<T> rows;
}
