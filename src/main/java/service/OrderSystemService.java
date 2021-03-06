package service;

import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import model.Product;
import model.StockNote;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import javax.annotation.Resource;
import java.util.List;

/*
  N Lankshear. s3529801. SEPT M1.
 */

@Transactional
@Service
public class OrderSystemService {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    //generic
    public void save(Object object){
	sessionFactory.getCurrentSession().save(object);
    }
    
    public void saveProduct(Product product){
	sessionFactory.getCurrentSession().saveOrUpdate(product);
    }
   
    public void saveStockNote(StockNote stocknote){
	sessionFactory.getCurrentSession().saveOrUpdate(stocknote);       
    }
    public void mergeStockNote(StockNote stocknote){
	sessionFactory.getCurrentSession().merge(stocknote);       
    }

    
    public Product getProductByPcode(String code){

	Query query = sessionFactory.getCurrentSession().createQuery("from Product where code = :code ");
	query.setString("code", code);
      
	return (Product) query.uniqueResult();
    }
    
    public List<Product> getAllProducts(){
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
	return criteria.list();
    }

    public List<StockNote> getAllStockNotes(){  
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StockNote.class);
	return criteria.list();
    }
    public StockNote getStockNoteByPCode(String pcode ){
	Query query = sessionFactory.getCurrentSession().createQuery("from StockNote where pcode=:pcode");
	query.setString("pcode", pcode);
	StockNote stocknote = (StockNote) query.uniqueResult();
	return stocknote;
    }
    
    public void deleteProduct(String code){
	Query query = sessionFactory.getCurrentSession().createQuery("from Product where code=:code");
	query.setString("code", code);
	Product product = (Product) query.uniqueResult();
	sessionFactory.getCurrentSession().delete(product);
    }

    public void deleteStockNote(String pcode){
	Query query = sessionFactory.getCurrentSession().createQuery("from StockNote where pcode=:pcode");
	query.setString("pcode", pcode);
	StockNote stocknote = (StockNote) query.uniqueResult();
	sessionFactory.getCurrentSession().delete(stocknote);
    }
}

