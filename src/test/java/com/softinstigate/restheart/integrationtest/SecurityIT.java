/*
 * Copyright SoftInstigate srl. All Rights Reserved.
 *
 *
 * The copyright to the computer program(s) herein is the property of
 * SoftInstigate srl, Italy. The program(s) may be used and/or copied only
 * with the written permission of SoftInstigate srl or in accordance with the
 * terms and conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied. This copyright notice must not be removed.
 */
package com.softinstigate.restheart.integrationtest;

import com.softinstigate.restheart.hal.Representation;
import com.softinstigate.restheart.utils.HttpStatus;
import io.undertow.util.Headers;
import junit.framework.Assert;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;

/**
 *
 * @author uji
 */
public class SecurityIT extends AbstactIT
{
    public SecurityIT()
    {
    }
    
    @Test
    public void testAuthentication() throws Exception
    {
        Response resp = adminExecutor.execute(Request.Get(rootUri));
        
        HttpResponse httpResp = resp.returnResponse();
        Assert.assertNotNull(httpResp);
        
        StatusLine statusLine = httpResp.getStatusLine();
        Assert.assertNotNull(statusLine);
        
        Assert.assertEquals("check authorized", HttpStatus.SC_OK, statusLine.getStatusCode());
    }
    
    @Test
    public void testGetUnauthenticated() throws Exception
    {
        // *** GET root
        Response resp = unauthExecutor.execute(Request.Get(rootUri));
        check("check get root unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** GET db
        resp = unauthExecutor.execute(Request.Get(dbUri));
        check("check get db unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** GET coll1
        resp = unauthExecutor.execute(Request.Get(collection1Uri));
        check("check get coll1 unauthorized", resp, HttpStatus.SC_OK);
        
        // *** GET doc1
        resp = unauthExecutor.execute(Request.Get(document1Uri));
        check("check get doc1 unauthorized", resp, HttpStatus.SC_OK);
        
         // *** GET coll2
        resp = unauthExecutor.execute(Request.Get(collection2Uri));
        check("check get coll2 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** GET doc2
        resp = unauthExecutor.execute(Request.Get(document2Uri));
        check("check get doc2 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
    }
    
    @Test
    public void testPostUnauthenticated() throws Exception
    {
        // *** POST coll1
        Response resp = unauthExecutor.execute(Request.Post(collection1Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check post coll1 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
         // *** GET coll2
        resp = unauthExecutor.execute(Request.Post(collection2Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check post coll2b unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
    }
    
    @Test
    public void testPutUnauthenticated() throws Exception
    {
        // *** PUT root
        Response resp = unauthExecutor.execute(Request.Put(rootUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check put root unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** PUT db
        resp = unauthExecutor.execute(Request.Put(dbUri).bodyString("{a:1}", halCT));
        check("check put db unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** PUT coll1
        resp = unauthExecutor.execute(Request.Put(collection1Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check put coll1 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** PUT doc1
        resp = unauthExecutor.execute(Request.Put(document1Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check put doc1 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
         // *** PUT coll2
        resp = unauthExecutor.execute(Request.Put(collection2Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check put coll2 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** PUT doc2
        resp = unauthExecutor.execute(Request.Put(document2Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check put doc2 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
    }
    
    @Test
    public void testPatchUnauthenticated() throws Exception
    {
        // *** PATCH root
        Response resp = unauthExecutor.execute(Request.Patch(rootUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check patch root unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** PATCH db
        resp = unauthExecutor.execute(Request.Patch(dbUri).bodyString("{a:1}", halCT));
        check("check patch db unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** PATCH coll1
        resp = unauthExecutor.execute(Request.Patch(collection1Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check patch coll1 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** PATCH doc1
        resp = unauthExecutor.execute(Request.Patch(document1Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check patch doc1 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
         // *** PATCH coll2
        resp = unauthExecutor.execute(Request.Patch(collection2Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check patch coll2 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** PATCH doc2
        resp = unauthExecutor.execute(Request.Patch(document2Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check patch doc2 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
    }
    
    @Test
    public void testDeleteUnauthenticated() throws Exception
    {
        // *** DELETE root
        Response resp = unauthExecutor.execute(Request.Delete(rootUri));
        check("check delete root unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** DELETE db
        resp = unauthExecutor.execute(Request.Delete(dbUri));
        check("check delete db unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** DELETE coll1
        resp = unauthExecutor.execute(Request.Delete(collection1Uri));
        check("check delete coll1 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** DELETE doc1
        resp = unauthExecutor.execute(Request.Delete(document1Uri));
        check("check delete doc1 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
         // *** DELETE coll2
        resp = unauthExecutor.execute(Request.Delete(collection2Uri));
        check("check delete coll2 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** DELETE doc2
        resp = unauthExecutor.execute(Request.Delete(document2Uri));
        check("check delete doc2 unauthorized", resp, HttpStatus.SC_UNAUTHORIZED);
    }
    
    @Test
    public void testGetAsAdmin() throws Exception
    {
        // *** GET root
        Response resp = adminExecutor.execute(Request.Get(rootUri));
        check("check get root as admin", resp, HttpStatus.SC_OK);
        
        // *** GET db
        resp = adminExecutor.execute(Request.Get(dbUri));
        check("check get db as admin", resp, HttpStatus.SC_OK);
        
        // *** GET coll1
        resp = adminExecutor.execute(Request.Get(collection1Uri));
        check("check get coll1 as admin", resp, HttpStatus.SC_OK);
        
        // *** GET doc1
        resp = adminExecutor.execute(Request.Get(document1Uri));
        check("check get doc1 as admin", resp, HttpStatus.SC_OK);
        
         // *** GET coll2
        resp = adminExecutor.execute(Request.Get(collection2Uri));
        check("check get coll2 as admin", resp, HttpStatus.SC_OK);
        
        // *** GET doc2
        resp = adminExecutor.execute(Request.Get(document2Uri));
        check("check get doc2 as admin", resp, HttpStatus.SC_OK);
    }
    
    @Test
    public void testPostAsAdmin() throws Exception
    {
        // *** POST coll1
        Response resp = adminExecutor.execute(Request.Post(collection1Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check post coll1 as admin", resp, HttpStatus.SC_CREATED);
        
         // *** POST coll2
        resp = adminExecutor.execute(Request.Post(collection2Uri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
        check("check post coll2b asadmin", resp, HttpStatus.SC_CREATED);
    }
    
    @Test
    public void testPutAsAdmin() throws Exception
    {
        try
        {
            // *** PUT root
            Response resp = adminExecutor.execute(Request.Put(rootUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put root as admin", resp, HttpStatus.SC_METHOD_NOT_ALLOWED);

            // *** PUT tmpdb
            resp = adminExecutor.execute(Request.Put(dbTmpUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put db as admin", resp, HttpStatus.SC_CREATED);

            // *** PUT tmpcoll
            resp = adminExecutor.execute(Request.Put(collectionTmpUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put coll1 as admin", resp, HttpStatus.SC_CREATED);

            // *** PUT doc1
            resp = adminExecutor.execute(Request.Put(documentTmpUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put doc1 as admin", resp, HttpStatus.SC_CREATED);
        }
        finally
        {
            mongoClient.dropDatabase(dbTmpName);
        }
    }
    
    @Test
    public void testGetAsPowerUser() throws Exception
    {
        // *** GET root
        Response resp = user1Executor.execute(Request.Get(rootUri));
        check("check get root as user1", resp, HttpStatus.SC_UNAUTHORIZED);
        
        // *** GET db
        resp = user1Executor.execute(Request.Get(dbUri));
        check("check get db as user1", resp, HttpStatus.SC_OK);
        
        // *** GET coll1
        resp = user1Executor.execute(Request.Get(collection1Uri));
        check("check get coll1 as user1", resp, HttpStatus.SC_OK);
        
        // *** GET doc1
        resp = user1Executor.execute(Request.Get(document1Uri));
        check("check get doc1 as user1", resp, HttpStatus.SC_OK);
        
         // *** GET coll2
        resp = user1Executor.execute(Request.Get(collection2Uri));
        check("check get coll2 as user1", resp, HttpStatus.SC_OK);
        
        // *** GET doc2
        resp = user1Executor.execute(Request.Get(document2Uri));
        check("check get doc2 as user1", resp, HttpStatus.SC_OK);
    }
    
    @Test
    public void testPutAsPowerUser() throws Exception
    {
        try
        {
            // *** PUT root
            Response resp = user1Executor.execute(Request.Put(rootUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put root as user1", resp, HttpStatus.SC_UNAUTHORIZED);

            // *** PUT db
            resp = user1Executor.execute(Request.Put(dbUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put db as user1", resp, HttpStatus.SC_UNAUTHORIZED);
            
            // *** PUT tmpdb
            resp = user1Executor.execute(Request.Put(dbTmpUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put db as user1", resp, HttpStatus.SC_CREATED);
            
            // *** PUT tmpcoll
            resp = user1Executor.execute(Request.Put(collectionTmpUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put coll1 as user1", resp, HttpStatus.SC_CREATED);

            // *** PUT doc1
            resp = user1Executor.execute(Request.Put(documentTmpUri).bodyString("{a:1}", halCT).addHeader(Headers.CONTENT_TYPE_STRING, Representation.HAL_JSON_MEDIA_TYPE));
            check("check put doc1 as user1", resp, HttpStatus.SC_CREATED);
        }
        finally
        {
            mongoClient.dropDatabase(dbTmpName);
        }
    }
}