package steven.test.project.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import steven.test.project.zhao.Dept;
import steven.test.project.vo.*;

import java.util.List;

// ========= 转换参数get set用法 ==========
@Mapper
public interface DeptConvert {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

    // DO → RespVO
    // DeptRespVO convert(Dept bean);
    List<DeptRespVO> convertList(List<Dept> list);

    // CreateReqVO → DO
    Dept convert(DeptCreateReqVO bean);

    // UpdateReqVO → DO
    Dept convert(DeptUpdateReqVO bean);
}