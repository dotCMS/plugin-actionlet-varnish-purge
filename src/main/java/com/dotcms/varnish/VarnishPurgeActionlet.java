package com.dotcms.varnish;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.dotmarketing.beans.Identifier;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.structure.model.Structure;
import com.dotmarketing.portlets.workflows.actionlet.WorkFlowActionlet;
import com.dotmarketing.portlets.workflows.model.WorkflowActionClassParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowActionFailureException;
import com.dotmarketing.portlets.workflows.model.WorkflowActionletParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowProcessor;
import com.dotmarketing.util.Logger;

public class VarnishPurgeActionlet extends WorkFlowActionlet {

	private static final long serialVersionUID = 1L;

	@Override
	public List<WorkflowActionletParameter> getParameters() {
		List<WorkflowActionletParameter> params = new ArrayList<WorkflowActionletParameter>();

		return params;
	}

	@Override
	public String getName() {
		return "Purge Varnish Cache";
	}

	@Override
	public String getHowTo() {
		return "Use the plugin properties to set the Varnish root url  : " + VarnishPurgePluginProperties.getProperty("ROOT_VARNISH_URL");
	}

	@Override
	public void executeAction(WorkflowProcessor processor, Map<String, WorkflowActionClassParameter> params)
			throws WorkflowActionFailureException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			String ROOT_VARNISH_URL = VarnishPurgePluginProperties.getProperty("ROOT_VARNISH_URL");

			Identifier id = APILocator.getIdentifierAPI().find(processor.getContentlet());

			String aURL = ROOT_VARNISH_URL + id.getPath();

			Logger.info(this.getClass(), "Purging :  " + aURL);



			HttpUriRequest purgeRequest = RequestBuilder.create("PURGE").setUri(aURL).build();
			httpclient.execute(purgeRequest);

		} catch (Exception e) {
			throw new WorkflowActionFailureException(e.getMessage(), e);
		}
		finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				Logger.error(this.getClass(), e.getMessage());
			}
		}

	}

}