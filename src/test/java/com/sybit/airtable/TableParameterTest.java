/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.test.WireMockBaseTest;
import java.util.List;
import org.apache.http.client.HttpResponseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableParameterTest extends WireMockBaseTest {
    
    @Test
    public void fieldsParamTest() throws AirtableException, HttpResponseException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        
        String[] fields = new String[1];
        fields[0] = "Name";
        
        List<Movie> listMovies = movieTable.select(fields);
        assertNotNull(listMovies);
        assertNull(listMovies.get(0).getDirector());
        assertNull(listMovies.get(0).getActors());
        assertNull(listMovies.get(0).getDescription());
    
    }
    
    @Test
    public void formulaParamTest() throws AirtableException, HttpResponseException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        
        Query query = new Query() {
            @Override
            public String[] getFields() {
                return null;
            }

            @Override
            public Integer getPageSize() {
                return null;
            }

            @Override
            public Integer getMaxRecords() {
                return null;
            }

            @Override
            public String getView() {
                return null;
            }

            @Override
            public List<Sort> getSort() {
                return null;
            }

            @Override
            public String filterByFormula() {
                return "NOT({Name} = '')";
            }
        };
         
        List<Movie> listMovies = movieTable.select(query);
        assertNotNull(listMovies);
        assertEquals(listMovies.size(),2);
  
    
    }
    
    @Test
    public void maxRecordsParamTest() throws AirtableException, HttpResponseException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        
        int maxRecords = 2;
         
        List<Movie> listMovies = movieTable.select(maxRecords);
        assertNotNull(listMovies);
        assertEquals(listMovies.size(),2);
        
    }
    
    @Test
    public void pageSizeParamTest() throws AirtableException, HttpResponseException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        
        Query query = new Query() {
            @Override
            public String[] getFields() {
                return null;
            }

            @Override
            public Integer getPageSize() {
                return 10;
            }

            @Override
            public Integer getMaxRecords() {
                return null;
            }

            @Override
            public String getView() {
                return null;
            }

            @Override
            public List<Sort> getSort() {
                return null;
            }

            @Override
            public String filterByFormula() {
                return null;
            }
        };
         
        List<Movie> listMovies = movieTable.select(query);
        assertNotNull(listMovies);
      
    }
    
    @Test
    public void sortParamTest() throws AirtableException, HttpResponseException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Sort sort = new Sort("Name", Sort.Direction.desc);
         
        List<Movie> listMovies = movieTable.select(sort);
        assertNotNull(listMovies);
        assertEquals(listMovies.get(9).getName(),"Billy Madison");
     
    
    }
    
    @Test
    public void viewParamTest() throws AirtableException, HttpResponseException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        
        String view = "Dramas";
         
        
        List<Movie> listMovies = movieTable.select(view);
        assertNotNull(listMovies);
        assertEquals(listMovies.size(),5);
        
    }
    
    
    
}
