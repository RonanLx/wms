/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.excel.service;

import com.itheima.wms.model.vo.biz.ImportResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface LocationExcelService {

    ImportResultVO analysis(MultipartFile file);
}
