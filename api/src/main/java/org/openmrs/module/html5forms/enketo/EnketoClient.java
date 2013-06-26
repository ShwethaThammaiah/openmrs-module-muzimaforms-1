package org.openmrs.module.html5forms.enketo;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.AbstractHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class EnketoClient {
    String enketoServiceUri;
    HttpClient httpClient;

    public EnketoClient(String enketoServiceUri, HttpClient defaultHttpClient) {
        this.enketoServiceUri = enketoServiceUri;
        this.httpClient = defaultHttpClient;
    }

    public String transform(String xformXml) throws IOException {
        File file = createTempFile(xformXml);
        HttpPost post = createPost(file);
        InputStream response = httpClient.execute(post).getEntity().getContent();
        file.delete();
        return IOUtils.toString(response);
    }

    private HttpPost createPost(File file) {
        HttpPost post = new HttpPost(enketoServiceUri);
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("xml_file", new FileBody(file));
        post.setEntity(entity);
        return post;
    }

    private File createTempFile(String xformXml) throws IOException {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        File file = new File(uuid + ".xml");
        FileUtils.writeStringToFile(file, xformXml);
        return file;
    }
}
