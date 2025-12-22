package steven.test.project.convert;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import steven.test.project.vo.DeptCreateReqVO;
import steven.test.project.vo.DeptRespVO;
import steven.test.project.vo.DeptUpdateReqVO;
import steven.test.project.zhao.Dept;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2025-12-22T17:22:09+0800", comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)")
public class DeptConvertImpl implements DeptConvert {

    @Override
    public List<DeptRespVO> convertList(List<Dept> list) {
        if (list == null) {
            return null;
        }

        List<DeptRespVO> list1 = new ArrayList<DeptRespVO>(list.size());
        for (Dept dept : list) {
            list1.add(deptToDeptRespVO(dept));
        }

        return list1;
    }

    @Override
    public Dept convert(DeptCreateReqVO bean) {
        if (bean == null) {
            return null;
        }

        Dept dept = new Dept();

        dept.setCount(bean.getCount());
        dept.setName(bean.getName());
        dept.setRemark(bean.getRemark());

        return dept;
    }

    @Override
    public Dept convert(DeptUpdateReqVO bean) {
        if (bean == null) {
            return null;
        }

        Dept dept = new Dept();

        dept.setCount(bean.getCount());
        dept.setId(bean.getId());
        dept.setName(bean.getName());
        dept.setRemark(bean.getRemark());

        return dept;
    }

    protected DeptRespVO deptToDeptRespVO(Dept dept) {
        if (dept == null) {
            return null;
        }

        DeptRespVO deptRespVO = new DeptRespVO();

        deptRespVO.setCount(dept.getCount());
        deptRespVO.setCreateTime(dept.getCreateTime());
        deptRespVO.setId(dept.getId());
        deptRespVO.setName(dept.getName());
        deptRespVO.setRemark(dept.getRemark());
        deptRespVO.setUpdateTime(dept.getUpdateTime());

        return deptRespVO;
    }
}
