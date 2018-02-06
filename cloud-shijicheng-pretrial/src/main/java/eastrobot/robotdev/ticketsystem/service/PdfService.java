package eastrobot.robotdev.ticketsystem.service;

/**
 * pdf service
 * @author luotao
 *
 */
public interface PdfService {
	//get unmarried info
	public byte[] getMigrantUnMarried(Integer muId);
	//get married info
	public byte[] getMigrantMarried(Integer muId);
	//get preproductive civics info
	public byte[] getPreproductiveCivics(Integer muId);
	
	//get company recruitment info
	public byte[] getCompanyRecruitmentDetail(Integer jobId);
	//get personal job hunting info
	public byte[] getPersonalJobHuntingDetail(Integer jobId);
	//get poll detail info
	public byte[] getPollDetail(Integer surveyId);
}
