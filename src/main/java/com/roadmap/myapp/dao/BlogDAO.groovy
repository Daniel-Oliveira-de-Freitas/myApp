package com.roadmap.myapp.dao

import com.owse.searchFramework.AbstractDAO
import com.owse.searchFramework.Filter
import com.owse.searchFramework.FilterDef
import com.owse.searchFramework.ResultColumn
import com.owse.searchFramework.StringFilterDef
import com.owse.searchFramework.enumeration.FilterType
import com.roadmap.myapp.domain.Blog
import com.roadmap.myapp.service.dto.BlogSearchDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

import javax.persistence.EntityManager

@Service("blogDAO")
class BlogDAO extends AbstractDAO<BlogSearchDTO> {

    private final Logger log = LoggerFactory.getLogger(BlogDAO.class)

    BlogDAO(EntityManager entityManager) {
        super(entityManager, Blog.class, BlogSearchDTO.class)
    }

    @Override
    List<String> getConstructorFields() {
            List<String> fields = []
            fields << 'entity.id'
            fields << 'entity.name'
            fields << 'entity.handle'
            fields << 'entity.user.login'
            return fields
        }




    @Override
    List<FilterDef> getFiltersDef() {
        List<Filter> filters = []
        filters << new StringFilterDef(id: 'id', filterType: FilterType.DEFAULT)
        return filters
    }

    @Override
    List<ResultColumn> getResultColumns() {
        List<ResultColumn> resultColumns = []
        resultColumns << new ResultColumn(id:'id', title: 'Id', dtoField: 'id', visible: true, type: 'Long')
        resultColumns << new ResultColumn(id:'name', title: 'Name', dtoField: 'name', visible: true, type: 'String')
        resultColumns << new ResultColumn(id:'handle', title: 'Handle', dtoField: 'handle', visible: true, type: 'String')
        resultColumns << new ResultColumn(id:'user', title: 'User', dtoField: 'user', visible: true, type: 'String')

        return resultColumns


    }

    @Override
    String getDefaultSortByField() {
        return "name"
    }

    @Override
    void configureCustomHQLFields() {

    }

    @Override
    Logger getLog() {
        return log
    }

    @Override
    List<String> getParamsId() {
        return null
    }
}
