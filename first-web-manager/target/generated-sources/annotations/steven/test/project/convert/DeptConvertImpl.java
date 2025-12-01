package steven.test.project.convert;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import steven.test.project.vo.DeptCreateReqVO;
import steven.test.project.vo.DeptRespVO;
import steven.test.project.vo.DeptUpdateReqVO;
import steven.test.project.zhao.Dept;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-01T14:29:38+0800",
    comments = "version: 1.6.0, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
public class DeptConvertImpl implements DeptConvert {

    @Override
    public List<DeptRespVO> convertList(List<Dept> list) {
        if ( list == null ) {
            return null;
        }

        List<DeptRespVO> list1 = new ArrayList<DeptRespVO>( list.size() );
        for ( Dept dept : list ) {
            list1.add( deptToDeptRespVO( dept ) );
        }

        return list1;
    }

    @Override
    public Dept convert(DeptCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        Dept dept = new Dept();

        dept.setName( bean.getName() );
        dept.setCount( bean.getCount() );
        dept.setRemark( bean.getRemark() );

        return dept;
    }

    @Override
    public Dept convert(DeptUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        Dept dept = new Dept();

        dept.setId( bean.getId() );
        dept.setName( bean.getName() );
        dept.setCount( bean.getCount() );
        dept.setRemark( bean.getRemark() );

        return dept;
    }

    protected DeptRespVO deptToDeptRespVO(Dept dept) {
        if ( dept == null ) {
            return null;
        }

        DeptRespVO deptRespVO = new DeptRespVO();

        deptRespVO.setId( dept.getId() );
        deptRespVO.setName( dept.getName() );
        deptRespVO.setCount( dept.getCount() );
        deptRespVO.setRemark( dept.getRemark() );
        deptRespVO.setCreateTime( dept.getCreateTime() );
        deptRespVO.setUpdateTime( dept.getUpdateTime() );

        return deptRespVO;
    }
}
