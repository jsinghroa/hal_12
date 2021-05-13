package com.wipro.iaf.emms.form;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PlusaMasterPmMeteriter {
	
	private String  frequency;
	private String iterationfreq;
	private String iterationseq;
	private String metername;
	private String plusamasterpmmeteriterid;
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getIterationfreq() {
		return iterationfreq;
	}
	public void setIterationfreq(String iterationfreq) {
		this.iterationfreq = iterationfreq;
	}
	public String getIterationseq() {
		return iterationseq;
	}
	public void setIterationseq(String iterationseq) {
		this.iterationseq = iterationseq;
	}
	public String getMetername() {
		return metername;
	}
	public void setMetername(String metername) {
		this.metername = metername;
	}
	public String getPlusamasterpmmeteriterid() {
		return plusamasterpmmeteriterid;
	}
	public void setPlusamasterpmmeteriterid(String plusamasterpmmeteriterid) {
		this.plusamasterpmmeteriterid = plusamasterpmmeteriterid;
	}
	@Override
	public String toString() {
		return "PlusaMasterPmMeteriter [frequency=" + frequency
				+ ", iterationfreq=" + iterationfreq + ", iterationseq="
				+ iterationseq + ", metername=" + metername
				+ ", plusamasterpmmeteriterid=" + plusamasterpmmeteriterid
				+ "]";
	}
	
	


}
