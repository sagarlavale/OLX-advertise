package com.zensar.advertise.repository;

import com.zensar.advertise.entity.Advertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AdvertiseRepositoryImpl implements AdvertiseRepositoryCustom{
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Advertise> filter(int page,int size,String title, String createdBy, Integer category, Integer status, Double price, String dateCondition, String onDate, String fromDate, String toDate){
        StringBuilder statusQuery = new StringBuilder();

        statusQuery.append("");

        if (title != null){
            statusQuery.append(" AND advertise.title like '"+title+"' ");
        }
        if (createdBy != null){
            statusQuery.append(" AND advertise.createdBy like '"+createdBy+"' ");
        }
        if (category != null){
            statusQuery.append(" AND advertise.category = "+category+"");
        }
        if (status != null){
            statusQuery.append(" AND advertise.status = "+status+"");
        }
        if (price != null){
            statusQuery.append(" AND advertise.price = "+price+"");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("BETWEEN") && fromDate !=null && toDate !=null){
            statusQuery.append(" AND advertise.createdAt BETWEEN '"+fromDate+" 00:00:00' AND '"+toDate+" 23:59:59' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("LESS THAN")  && toDate !=null){
            statusQuery.append(" AND advertise.createdAt <= '"+toDate+" 23:59:00' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("GREATER THAN")  && fromDate !=null){
            statusQuery.append(" AND advertise.createdAt >= '"+fromDate+" 00:00:00' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("EQUAL")  && onDate !=null){
            statusQuery.append(" AND advertise.createdAt BETWEEN '"+onDate+" 00:00:00' AND '"+onDate+" 23:59:59' ");
        }

        javax.persistence.Query query = entityManager.createQuery(" SELECT advertise FROM Advertise  advertise WHERE advertise.id != null "+
                statusQuery.toString(),Advertise.class);

        query.setFirstResult((page) * size);
        query.setMaxResults(size);

        return  query.getResultList();

    }

    @Override
    public List<Advertise> search(String searchText) {
        StringBuilder statusQuery = new StringBuilder();

        statusQuery.append("");

        if (searchText != null){
            statusQuery.append(" OR advertise.title like '"+searchText+"' ");
        }
        if (searchText != null){
            statusQuery.append(" OR advertise.createdBy like '"+searchText+"' ");
        }
        if (searchText != null){
            statusQuery.append(" OR advertise.description like '"+searchText+"' ");
        }

        javax.persistence.Query query = entityManager.createQuery(" SELECT advertise FROM Advertise  advertise WHERE advertise.id != null "+
                statusQuery.toString(),Advertise.class);


        return  query.getResultList();

    }
}
