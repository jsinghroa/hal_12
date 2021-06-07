package com.wipro.iaf.emms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.wipro.iaf.emms.constants.Constants;
import com.wipro.iaf.emms.form.AssetConfigForm;
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.ErrorStatusForm;
import com.wipro.iaf.emms.form.FlbDetailForm;
import com.wipro.iaf.emms.form.FlbMeterDetailsForm;
import com.wipro.iaf.emms.form.FlbPostFlightDataForm;
import com.wipro.iaf.emms.form.FlbSortieArForm;
import com.wipro.iaf.emms.form.InstallableAssetForm;
import com.wipro.iaf.emms.form.MeterDetailsForm;
import com.wipro.iaf.emms.form.PMDetailForm;
import com.wipro.iaf.emms.form.TableStatusForm;
import com.wipro.iaf.emms.form.UserRegistrationForm;
import com.wipro.iaf.emms.validator.DateConvertor;
import com.wipro.iaf.emms.validator.MeterDetailsValidator;

public class HalDao {

	private static JdbcTemplate jdbcTemplate;

	private static List<String> statuses = new ArrayList<String>() {

		private static final long serialVersionUID = 1L;

		{
			add(Constants.DEFAULTPOSTFLIGHTSTATUS);
			add(Constants.ABORTPOSTFLIGHTSTATUS);
			add(Constants.CLOSEDPOSTFLIGHTSTATUS);

		}
	};

	private static List<String> codes = new ArrayList<String>() {

		private static final long serialVersionUID = 1L;

		{
			add("CAT A");
			add("CAT B");

		}
	};

	private static List<String> complianceStatusOptions = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("Performed");
			add("Not Performed");
		}
	};

	private static DateConvertor convertor = new DateConvertor();

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final class ListViewEmmsDataMapper implements RowMapper<EmmsDataForm> {

		public EmmsDataForm mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			System.out.println("insideee");
			EmmsDataForm emmsDataForm = new EmmsDataForm();
			emmsDataForm.setRecordId(resultSet.getString("Record_ID"));
			emmsDataForm.setMainAsset(resultSet.getString("Main_Asset_Num"));
			emmsDataForm.setLocation(resultSet.getString("Location"));

			if (resultSet.getMetaData().getColumnCount() == 3) {
				System.out.println("3");
				emmsDataForm.setRecordIdStatus("All Table Data Imported");

			} else if (resultSet.getMetaData().getColumnCount() == 11) {
				System.out.println("inside11");
				emmsDataForm.setSignalOutDate(convertor.getDateTime1(resultSet.getString("Signal_Out_Date")));
				emmsDataForm.setInductionDate(convertor.getDateTime1(resultSet.getString("Induction_Date")));
				emmsDataForm.setMainAssetPart(resultSet.getString("Existing_Asset_CM_Item"));
				emmsDataForm.setMainAssetSerial(resultSet.getString("Existing_Asset_Serial"));
				emmsDataForm.setDesc(resultSet.getString("CM_Item_Desc"));
				emmsDataForm.setRecordStatus(resultSet.getString("Record_Status"));
				emmsDataForm.setFreeze(resultSet.getBoolean("Freeze"));
				emmsDataForm.setFlbNum(resultSet.getString("flb_num"));
			} else if (resultSet.getMetaData().getColumnCount() == 12) {
				System.out.println("inside12:" + resultSet.getString("Signal_Out_Date"));
				emmsDataForm.setSignalOutDate(convertor.getDateTime1(resultSet.getString("Signal_Out_Date")));
				emmsDataForm.setInductionDate(convertor.getDateTime1(resultSet.getString("Induction_Date")));
				emmsDataForm.setMainAssetPart(resultSet.getString("Main_Asset_Num"));
				/*
				 * emmsDataForm.setMainAssetSerial(resultSet .getString("Main_Asset_Num"));
				 * emmsDataForm.setDesc(resultSet.getString("Main_Asset_Num"));
				 */
				emmsDataForm.setRecordStatus(resultSet.getString("Record_Status"));
				emmsDataForm.setModel(resultSet.getString("model"));
				emmsDataForm.setAssetConfigStatus(resultSet.getString("Asset_Cfg_Status"));
				emmsDataForm.setInstallableStatus(resultSet.getString("Installable_Status"));
				emmsDataForm.setAssetMeterStatus(resultSet.getString("Asset_Meter_Status"));
				emmsDataForm.setAssetPmStatus(resultSet.getString("Asset_PM_Status"));
				emmsDataForm.setFreeze(resultSet.getBoolean("Freeze"));

			}
			return emmsDataForm;
		}
	}

	public List<EmmsDataForm> getEmmsDataOnView() {
		String query = "select a.Record_ID,a.Main_Asset_Num,a.flb_num,a.Location,a.Signal_Out_Date,a.Induction_Date,b.Existing_Asset_CM_Item,"
				+ "b.Existing_Asset_Serial,c.CM_Item_Desc,a.Record_Status,a.Freeze from asset_details as a, asset_cfg_actual as b"
				+ ", asset_model as c " + " where  a.Record_ID=b.Record_ID group by a.Record_ID";
		System.out.println(query);
		return this.jdbcTemplate.query(query, new ListViewEmmsDataMapper());

	}

	public List<EmmsDataForm> getRecordIdOnView() {
		String query = "select  a.Record_ID,a.Main_Asset_Num,a.Location from asset_details "
				+ "as a group by a.record_id";

		return this.jdbcTemplate.query(query, new ListViewEmmsDataMapper());
	}

	public List<EmmsDataForm> getRecordIdOnExport() {
		String query = "select  a.Record_ID,a.Main_Asset_Num,a.Location from asset_details "
				+ "as a where a.Asset_PM_Status='Validation Completed' group by a.record_id";

		return this.jdbcTemplate.query(query, new ListViewEmmsDataMapper());
	}

	public EmmsDataForm fetchDetails(String selectedRecordId) {
		String query = "select a.Record_ID,a.Main_Asset_Num,a.Location,a.Signal_Out_Date,a.Induction_Date,a.model,a.Record_Status,"
				+ " a.Asset_Cfg_Status,a.Installable_Status,a.Asset_Meter_Status,a.Asset_PM_Status,a.Freeze from asset_details as a"
				+ " where a.record_ID=?";
		return this.jdbcTemplate.queryForObject(query, new Object[] { selectedRecordId }, new ListViewEmmsDataMapper());
	}

	public void deleteData(String deleterecordId) {
		String fetchQuery = "select new_record_id from record_id_relationship where record_id=?";
		String refRecordid = this.jdbcTemplate.queryForObject(fetchQuery, new Object[] { deleterecordId },
				String.class);

		this.jdbcTemplate.update("delete from asset_details where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from asset_model where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from asset_cfg_plan where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from asset_cfg_actual where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from asset_pm where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from asset_meter_plan where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from asset_meter_actual where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from flb_details where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from flb_post_flt_details where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from flb_sortie_accept where Record_ID=?", refRecordid);
		this.jdbcTemplate.update("delete from record_id_relationship where new_Record_ID=?", refRecordid);

	}

	public void deleteRecord(String deleterecordId) {
		this.jdbcTemplate.update("delete from asset_details where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from asset_model where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from asset_cfg_plan where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from asset_cfg_actual where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from asset_pm where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from asset_meter_plan where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from asset_meter_actual where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from flb_details where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from flb_post_flt_details where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from flb_sortie_accept where Record_ID=?", deleterecordId);
		this.jdbcTemplate.update("delete from record_id_details where Record_ID=?", deleterecordId);

	}

	public String fetchrecordStatus(String deleterecordId) {
		String fetchQuery = "select new_record_id from record_id_relationship where record_id=?";
		String refRecordid = null;
		try {
			refRecordid = this.jdbcTemplate.queryForObject(fetchQuery, new Object[] { deleterecordId }, String.class);
		} catch (Exception e) {

		}
		return refRecordid;
	}

	public List<AssetConfigForm> fetchConfigDetails(String recordID) {

		/*
		 * String sql =
		 * "select * from asset_cfg_actual,asset_details where asset_cfg_actual.record_id=asset_details.record_id AND "
		 * + "asset_details.record_id=? ";
		 */

		/*
		 * String
		 * sql="select * from asset_cfg_actual,asset_details where asset_cfg_actual.record_ID = asset_details.record_id and "
		 * +"asset_details.record_id=? and " + " asset_cfg_actual.model in"+
		 * "( select distinct asset_cfg_actual.model from asset_cfg_actual,asset_details where asset_cfg_actual.record_id = asset_details.record_id and indicator='I')"
		 * ;
		 */

		/*
		 * String sql =
		 * "select * from asset_cfg_actual,asset_details where asset_cfg_actual.record_id=asset_details.record_id AND "
		 * + "asset_details.record_id=? ";
		 */

		String sql = "select * from asset_cfg_actual,asset_details where asset_cfg_actual.record_ID = asset_details.record_id and "
				+ "asset_details.record_id=? and " + "asset_cfg_actual.model=asset_details.model";

		return this.jdbcTemplate.query(sql, new Object[] { recordID }, new AssetListViewMapper());
	}

	private static final class AssetListViewMapper implements RowMapper<AssetConfigForm> {
		public AssetConfigForm mapRow(ResultSet rs, int row) throws SQLException {

			AssetConfigForm assetConfigForm = new AssetConfigForm();
			assetConfigForm.setRecordId(rs.getString("Record_ID"));
			assetConfigForm.setBuildItem(rs.getString("build_item"));
			assetConfigForm.setAssetNum(rs.getString("Existing_Asset_Num"));

			assetConfigForm.setIndicator(rs.getString("indicator"));
			assetConfigForm.setLcn(rs.getString("lcn"));
			assetConfigForm.setPartDescription(rs.getString("build_item"));
			assetConfigForm.setPartNum(rs.getString("Existing_Asset_CM_Item"));
			assetConfigForm.setPosition(rs.getString("build_position"));
			assetConfigForm.setSerialNum(rs.getString("Existing_Asset_Serial"));
			assetConfigForm.setConditionCode(rs.getString("condition_code"));//
			assetConfigForm.setDateOfManfacturing(convertor.getDateTime1(rs.getString("mfg_date")));
			assetConfigForm.setDateOfReciept(convertor.getDateTime1(rs.getString("receipt_date")));
			assetConfigForm.setErrorDesc(rs.getString("error_desc"));//
			assetConfigForm.setErrorStatus(rs.getString("error_status"));//
			assetConfigForm.setInLieuPn(rs.getString("New_In_Lieu_Part"));//
			assetConfigForm.setInstalledPN(rs.getString("Install_CM_Item"));//
			assetConfigForm.setInstalledSN(rs.getString("Install_Serial_Item"));//
			assetConfigForm.setInstalledPNList(getInstalledPn(rs.getString("build_item"), rs.getString("Record_ID")));//
			assetConfigForm.setConditionCodes(codes);
			assetConfigForm.setRecordRowId(rs.getString("Record_Row_ID"));
			assetConfigForm.setBiType("Logicals");
			return assetConfigForm;
		}

	}

	public List<InstallableAssetForm> fetchInstallableDetails(String recordID) {

		String sql = "select * from asset_cfg_actual,asset_details where asset_cfg_actual.record_ID = asset_details.record_id and "
				+ "asset_details.record_id=? and " + " asset_cfg_actual.model in"
				+ "( select distinct CM_Item from asset_cfg_actual,asset_details where asset_cfg_actual.record_ID = asset_details.record_id and asset_cfg_actual.indicator='I')";
		return this.jdbcTemplate.query(sql, new Object[] { recordID }, new InstallableListViewMapper());
	}

	private static final class InstallableListViewMapper implements RowMapper<InstallableAssetForm> {
		public InstallableAssetForm mapRow(ResultSet rs, int row) throws SQLException {
			InstallableAssetForm installableForm = new InstallableAssetForm();

			installableForm.setRecordId(rs.getString("Record_ID"));
			installableForm.setBuilditem(rs.getString("build_item"));
			installableForm.setAssertnum(rs.getString("Existing_Asset_Num"));

			installableForm.setLcn(rs.getString("lcn"));
			installableForm.setPartDesc(rs.getString("build_item"));
			installableForm.setPartnum(rs.getString("Existing_Asset_CM_Item"));
			installableForm.setPosition(rs.getString("build_position"));
			installableForm.setSerialnum(rs.getString("Existing_Asset_Serial"));
			installableForm.setConditionCode(rs.getString("condition_code"));
			installableForm.setDateofManufacturing(convertor.getDateTime1(rs.getString("mfg_date")));
			installableForm.setDateofReceipt(convertor.getDateTime1(rs.getString("receipt_date")));
			installableForm.setErrorDescription(rs.getString("error_desc"));
			installableForm.setErrorStatus(rs.getString("error_status"));
			installableForm.setiNlieuPN(rs.getString("New_In_Lieu_Part"));

			installableForm.setInstallablePN(rs.getString("Install_CM_Item"));
			installableForm.setInstallableSN(rs.getString("Install_Serial_Item"));
			installableForm.setInstalledPNList(getInstalledPn(rs.getString("build_item"), rs.getString("Record_ID")));
			installableForm.setConditionCodes(codes);
			installableForm.setRecordRowId(rs.getString("Record_Row_ID"));

			return installableForm;
		}

	}

	public List<MeterDetailsForm> fetchMetersDetails(String recordID) {
		String sql = "select * from asset_meter_actual,asset_details where asset_details.freeze='1' and asset_meter_actual.install_cm_item!='' "
				+ "and asset_meter_actual.install_serial_num!='' and asset_meter_actual.record_id=asset_details.record_id AND "
				+ "asset_details.record_id=?";
		return this.jdbcTemplate.query(sql, new Object[] { recordID }, new MeterListViewMapper());
	}

	private static final class MeterListViewMapper implements RowMapper<MeterDetailsForm> {
		public MeterDetailsForm mapRow(ResultSet rs, int row) throws SQLException {

			MeterDetailsForm meterDetailsForm = new MeterDetailsForm();

			// NON EDITABLE
			meterDetailsForm.setMeterName(rs.getString("Meter_Name"));
			meterDetailsForm.setInstalledPartDescription(rs.getString("Build_Item"));
			meterDetailsForm.setInstalledPN(rs.getString("install_cm_item"));
			meterDetailsForm.setInstalledSN(rs.getString("install_serial_num"));
			meterDetailsForm.setRecordRowId(rs.getString("Record_Row_ID"));

			// EDITABLE
			meterDetailsForm.setCurrentCount(rs.getString("Current_Count"));
			// existingCount=Install_Count
			meterDetailsForm.setExistingCount(rs.getString("Install_Count"));

			// FUNCTIONAL NEED
			meterDetailsForm.setUom(rs.getString("UOM"));
			meterDetailsForm.setExAstCurrentCount(rs.getString("Ex_Ast_Current_Count"));
			meterDetailsForm.setExistingInstalledPn(rs.getString("Existing_asset_cm_item"));
			meterDetailsForm.setExistingInstalledSN(rs.getString("Existing_asset_serial"));
			meterDetailsForm.setExAstInstallCount(rs.getString("Ex_Ast_Install_Count"));
			meterDetailsForm.setExAstCurrentCount_HMS(rs.getString("Ex_Ast_Current_Count_HMS"));
			meterDetailsForm.setExAstInstallCountHMS(rs.getString("Ex_Ast_Install_Count_HMS"));
			meterDetailsForm.setError(rs.getString("Error_Status"));

			if (meterDetailsForm.getUom().equalsIgnoreCase("hh:mm:ss")) {

				if (null != meterDetailsForm.getExistingCount()) {

					meterDetailsForm.setExistingCount(
							getUomValue(meterDetailsForm.getExistingCount(), meterDetailsForm.getUom()));

				}
				if (null != meterDetailsForm.getCurrentCount()) {
					meterDetailsForm.setCurrentCount(
							getUomValue(meterDetailsForm.getCurrentCount(), meterDetailsForm.getUom()));

				}
			}
			return meterDetailsForm;
		}

	}

	public List<PMDetailForm> fetchPMDetails(String recordId) {
		String sql = "select * from asset_pm,asset_details where asset_details.freeze='1' and asset_pm.record_id=asset_details.record_id AND "
				+ "asset_details.record_id=? and Work_Type in ('EXP','OH')";
		return this.jdbcTemplate.query(sql, new Object[] { recordId }, new PMListViewMapper());

	}

	private static final class PMListViewMapper implements RowMapper<PMDetailForm> {
		public PMDetailForm mapRow(ResultSet rs, int row) throws SQLException {
			System.out.println("lsist viwew");
			PMDetailForm pmDetailForm = new PMDetailForm();
			pmDetailForm.setInstalledPN(rs.getString("Install_CM_Item"));
			pmDetailForm.setInstalledPartDesc("BUILD_ITEM");
			pmDetailForm.setInstallSerialNum(rs.getString("install_serial_num"));
			pmDetailForm.setMpmNum(rs.getString("mpm_num"));
			pmDetailForm.setMpmDescription(rs.getString("mpm_description"));
			pmDetailForm.setWorkType(rs.getString("work_type"));
			pmDetailForm.setMeterName(rs.getString("meter_name"));
			pmDetailForm.setUom(rs.getString("UOM"));
			pmDetailForm.setFrequencyUnit(rs.getString("frequency_unit"));
			pmDetailForm.setFrequencyIteration(rs.getInt("iterationcalfrequency"));
			pmDetailForm.setComplianceStatus(rs.getString("compliance_status"));
			pmDetailForm.setComplianceStatusOptions(complianceStatusOptions);
			pmDetailForm.setLastCompiledDate(convertor.getDate2(rs.getString("last_complied_date")));
			pmDetailForm.setNextDueDate(convertor.getDate2(rs.getString("next_due_date")));
			pmDetailForm.setLastCompiledValue(rs.getBigDecimal("last_complied_value"));
			pmDetailForm.setNextDueValue(rs.getBigDecimal("next_due_value"));
			pmDetailForm.setErrorStatus(rs.getString("error_status"));
			pmDetailForm.setErrorDesc(rs.getString("error_desc"));
			pmDetailForm.setRecordRowId(rs.getString("Record_Row_ID"));

			return pmDetailForm;
		}

	}

	// Flb Sortie
	public List<FlbSortieArForm> fetchFLBSortieARDetails(String recordID) {
		String sql = "select * from flb_sortie_accept,asset_details where asset_details.freeze='1' and flb_sortie_accept.record_id=asset_details.record_id AND "
				+ "asset_details.record_id=?";
		return this.jdbcTemplate.query(sql, new Object[] { recordID }, new FLBSortieArMapper());
	}

	private static final class FLBSortieArMapper implements RowMapper<FlbSortieArForm> {
		public FlbSortieArForm mapRow(ResultSet rs, int row) throws SQLException {
			FlbSortieArForm FLBForm = new FlbSortieArForm();

			FLBForm.setSortieStatus(rs.getString("Sortie_Status"));
			FLBForm.setSortieNo(rs.getString("Sortie_Num"));
			FLBForm.setSortieDate(convertor.getDate2(rs.getString("Sortie_Date")));
			FLBForm.setEtdDate(convertor.getDate2(rs.getString("Etd_Date")));
			FLBForm.setETD(rs.getString("ETD_IST"));
			FLBForm.setDuration(rs.getString("Duration"));
			FLBForm.setFlightType(rs.getString("Flight_Type"));
			FLBForm.setStatusDate(rs.getString("Sortie_Status_Date"));
			FLBForm.setChangedBy(rs.getString("Change_By"));
			FLBForm.setRemarks(rs.getString("Caution_Remarks"));
			FLBForm.setReason(rs.getString("Reason"));
			FLBForm.setAccept(rs.getString("Accept")); // not in db
			FLBForm.setReject(rs.getString("Reject")); // not in db
			FLBForm.setError(rs.getString("Error")); // not in db
			FLBForm.setErrordesc(rs.getString("Error_Description"));
			FLBForm.setRecordRowId(rs.getString("Record_Row_Id"));
			FLBForm.setRecordId(rs.getString("Record_ID"));

			return FLBForm;
		}

	}

	public List<FlbPostFlightDataForm> fetchFLBPostFlightDetails(String recordID) {
		String sql = "select * from asset_details,flb_post_flt_details where asset_details.freeze='1' and flb_post_flt_details.record_id=asset_details.record_id AND "
				+ "asset_details.record_id=?";
		return this.jdbcTemplate.query(sql, new Object[] { recordID }, new FLBPostFlightMapper());
	}

	private static final class FLBPostFlightMapper implements RowMapper<FlbPostFlightDataForm> {
		public FlbPostFlightDataForm mapRow(ResultSet rs, int row) throws SQLException {
			FlbPostFlightDataForm FLBForm = new FlbPostFlightDataForm();

			FLBForm.setFlbNo(rs.getString("FLB_Num"));
			FLBForm.setFlightNumber(rs.getString("Flt_Num"));
			FLBForm.setSortieNumber(rs.getString("Sortie_Num"));
			FLBForm.setRecordId(rs.getString("record_id"));
			FLBForm.setFlightDate(convertor.getDateTime2(rs.getString("Flt_date")));
			FLBForm.setFlightType(rs.getString("Flight_type"));
			FLBForm.setDepartureTime(rs.getString("Departure_Time"));
			FLBForm.setArrivalTime(rs.getString("Arrival_Time"));
			FLBForm.setFlightHours(rs.getString("Flt_Hrs"));
			FLBForm.setStatus(rs.getString("Flt_Status"));
			FLBForm.setCreationDate(rs.getString("Creation_Date"));
			FLBForm.setRecordRowId(rs.getString("record_row_id"));
			FLBForm.setStatuses(statuses);
			FLBForm.setError(rs.getString("error"));
			FLBForm.setFlightTypes(getFlightTypes("1001"));
			FLBForm.setListSortieNumber(getSortieNumbers("1001"));
			System.out.println(FLBForm.getFlightTypes());
			System.out.println(FLBForm.getListSortieNumber());
			return FLBForm;
		}

	}

	public List<FlbMeterDetailsForm> fetchFLBMeterDetails(String recordID) {

		String sql = "select * from asset_details,flb_details where flb_details.record_id=asset_details.record_id AND "
				+ "asset_details.record_id=?";
		return this.jdbcTemplate.query(sql, new Object[] { recordID }, new FLBMeterDetailsMapper());
	}

	private static final class FLBMeterDetailsMapper implements RowMapper<FlbMeterDetailsForm> {
		public FlbMeterDetailsForm mapRow(ResultSet rs, int row) throws SQLException {
			FlbMeterDetailsForm FLBForm = new FlbMeterDetailsForm();

			FLBForm.setLcn(rs.getString("LCN"));
			FLBForm.setBuilditem(rs.getString("Build_Item"));
			// FLBForm.setInstalledpn(rs.getString("Existing_Asset_CM_Item"));
			/*
			 * FLBForm.setMeterDetails(rs.getString("Meter_name")); //
			 * FLBForm.setLcn(rs.getString("LCN"));
			 * FLBForm.setInstalledpn(rs.getString("Install_CM_Item"));
			 * FLBForm.setInstalledsn(rs.getString("Install_Serial_Num"));
			 * FLBForm.setMeter(rs.getString("Meter"));
			 * FLBForm.setBuilditem(rs.getString("Build_Item"));
			 * 
			 * FLBForm.setDescription(rs.getString("Description"));
			 * FLBForm.setReadingType(rs.getString("Reading_Type")); //
			 * FLBForm.setUpdatedby(rs.getString("Updated_by")); //
			 * FLBForm.setError(rs.getString("Error")); //not in db
			 * FLBForm.setErrordesc(rs.getString("Error_Description"));
			 */
			// FLBForm.setMeterReading(rs.getString("Meter_Reading")); //

			// FLBForm.setReadingType(rs.getString("Reading_Type")); //
			// FLBForm.setUpdatedby(rs.getString("Updated_by")); //
			// FLBForm.setMeterReading(rs.getString("Meter_Reading")); //

			return FLBForm;
		}
	}

	public List<ErrorStatusForm> fetchErrors(String recordId) {
		String sql = "SELECT * FROM errors WHERE recordId=?";
		return this.jdbcTemplate.query(sql, new Object[] { recordId }, new ErrorListMapper());
	}

	private static final class ErrorListMapper implements RowMapper<ErrorStatusForm> {

		public ErrorStatusForm mapRow(ResultSet rs, int rowNum) throws SQLException {
			ErrorStatusForm errorStatusForm = new ErrorStatusForm();
			errorStatusForm.setConcatId(rs.getString("concatId"));
			errorStatusForm.setUserId(rs.getString("userId"));
			errorStatusForm.setZipName(rs.getString("zipName"));
			errorStatusForm.setXmlName(rs.getString("xmlName"));
			errorStatusForm.setErrors(rs.getString("errorList"));
			errorStatusForm.setTimestamp(rs.getString("timestamp"));
			return errorStatusForm;
		}
	}

	public int updateHeader(EmmsDataForm emmsDataForm) {
		String sql;
		sql = "update asset_details set asset_details.induction_date='" + emmsDataForm.getInductionDate() + "',"
				+ "asset_details.Asset_Meter_Status='" + emmsDataForm.getAssetMeterStatus() + "',"
				+ "asset_details.Record_Status='" + emmsDataForm.getRecordStatus()
				+ "', asset_details.signal_out_date='" + emmsDataForm.getSignalOutDate() + "'"
				+ " where asset_details.record_id='" + emmsDataForm.getRecordId() + "'";
		return jdbcTemplate.update(sql);
	}

	public int update(EmmsDataForm emmsDataForm, AssetConfigForm assetConfigForm) {

		String query = "UPDATE asset_details,asset_cfg_actual SET " + "asset_details.induction_date = ?, "
				+ "asset_details.signal_out_date = ?, " + "asset_details.Asset_Cfg_Status = ?, "
				+ "asset_details.Record_Status = ?, " + "asset_cfg_actual.mfg_date = ?, "
				+ "asset_cfg_actual.receipt_date = ?, " + "asset_cfg_actual.Install_CM_Item = ?,"
				+ "asset_cfg_actual.New_In_Lieu_Part = ?, " + "asset_cfg_actual.error_status = ?, "
				+ "asset_cfg_actual.Install_Serial_Item = ?, " + "asset_cfg_actual.condition_code = ?"
				+ "WHERE (asset_details.record_id=asset_cfg_actual.record_id AND asset_cfg_actual.Record_Row_ID=?);";

		String inductionDate = emmsDataForm.getInductionDate();
		String signalOutDate = emmsDataForm.getSignalOutDate();
		if (null != assetConfigForm.getDateOfReciept() && assetConfigForm.getDateOfReciept().isEmpty()) {
			assetConfigForm.setDateOfReciept(null);
		}

		if (null != assetConfigForm.getDateOfManfacturing() && assetConfigForm.getDateOfManfacturing().isEmpty()) {
			assetConfigForm.setDateOfManfacturing(null);
		}
		if (null != emmsDataForm.getInductionDate() && emmsDataForm.getInductionDate().isEmpty()) {
			inductionDate = null;
		}
		if (null != emmsDataForm.getSignalOutDate() && emmsDataForm.getSignalOutDate().isEmpty()) {
			signalOutDate = null;
		}

		int status = 0;
		try {
			status = jdbcTemplate.update(query, inductionDate, signalOutDate, emmsDataForm.getAssetConfigStatus(),
					emmsDataForm.getRecordStatus(), assetConfigForm.getDateOfManfacturing(),
					assetConfigForm.getDateOfReciept(), assetConfigForm.getInstalledPN(), assetConfigForm.getInLieuPn(),
					assetConfigForm.getErrorStatus(), assetConfigForm.getInstalledSN(),
					assetConfigForm.getConditionCode(), assetConfigForm.getRecordRowId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return status;
	}

	public int update(EmmsDataForm emmsDataForm, InstallableAssetForm insAsset) {

		String query = "UPDATE asset_details,asset_cfg_actual SET " + "asset_details.induction_date = ?, "
				+ "asset_details.signal_out_date = ?, " + "asset_details.Installable_Status = ?, "
				+ "asset_details.Record_Status = ?, " + "asset_cfg_actual.mfg_date = ?, "
				+ "asset_cfg_actual.receipt_date = ?, " + "asset_cfg_actual.Install_CM_Item = ?,"
				+ "asset_cfg_actual.New_In_Lieu_Part = ?, " + "asset_cfg_actual.error_status = ?, "
				+ "asset_cfg_actual.Install_Serial_Item = ?, " + "asset_cfg_actual.condition_code = ?"
				+ "WHERE (asset_details.record_id=asset_cfg_actual.record_id AND asset_cfg_actual.Record_Row_ID=?);";

		String inductionDate = emmsDataForm.getInductionDate();
		String signalOutDate = emmsDataForm.getSignalOutDate();
		if (null != insAsset.getDateofReceipt() && insAsset.getDateofReceipt().isEmpty()) {
			insAsset.setDateofReceipt(null);
		}

		if (null != insAsset.getDateofManufacturing() && insAsset.getDateofManufacturing().isEmpty()) {
			insAsset.setDateofManufacturing(null);
		}
		if (null != emmsDataForm.getInductionDate() && emmsDataForm.getInductionDate().isEmpty()) {
			inductionDate = null;
		}
		if (null != emmsDataForm.getSignalOutDate() && emmsDataForm.getSignalOutDate().isEmpty()) {
			signalOutDate = null;
		}

		int status = 0;
		try {
			status = jdbcTemplate.update(query, inductionDate, signalOutDate, emmsDataForm.getAssetConfigStatus(),
					emmsDataForm.getRecordStatus(), insAsset.getDateofManufacturing(), insAsset.getDateofReceipt(),
					insAsset.getInstallablePN(), insAsset.getiNlieuPN(), insAsset.getErrorStatus(),
					insAsset.getInstallableSN(), insAsset.getConditionCode(), insAsset.getRecordRowId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return status;

	}

	public int update(EmmsDataForm emmsDataForm, MeterDetailsForm meterForm) {

		if (meterForm.getCurrentCount().length() <= 0) {
			meterForm.setCurrentCount(null);
		}
		if (meterForm.getExistingCount().length() <= 0) {
			meterForm.setExistingCount(null);
		}

		String sql = "update asset_details,asset_meter_actual set asset_details.induction_date='"
				+ emmsDataForm.getInductionDate() + "', asset_details.signal_out_date='"
				+ emmsDataForm.getSignalOutDate() + "'," + "asset_details.Asset_Meter_Status='"
				+ emmsDataForm.getAssetMeterStatus() + "'," + "asset_details.Record_Status='"
				+ emmsDataForm.getRecordStatus() + "',asset_meter_actual.current_count=" + meterForm.getCurrentCount()
				+ ",asset_meter_actual.error_status='" + meterForm.getError() + "',"
				+ "asset_meter_actual.Install_Count=" + meterForm.getExistingCount() + ""
				+ " where asset_details.record_id=asset_meter_actual.record_id AND asset_meter_actual.Record_Row_ID='"
				+ meterForm.getRecordRowId() + "'";

		System.out.println("UpdateMeterQuery:" + sql);
		return jdbcTemplate.update(sql);
	}

	public int update(EmmsDataForm emmsDataForm, PMDetailForm pmForm) {

		String sql = "update asset_details set asset_details.Asset_PM_Status='" + emmsDataForm.getAssetPmStatus() + "'"
				+ " where asset_details.Record_ID='" + emmsDataForm.getRecordId() + "'";

		int temp = jdbcTemplate.update(sql);

		if (temp == 1) {
			if (pmForm.getLastCompiledDate().length() > 0 && pmForm.getNextDueDate().length() > 0) {
				sql = "update asset_pm set asset_pm.Last_Complied_Date='" + pmForm.getLastCompiledDate() + "',"
						+ "asset_pm.Next_Due_Date='" + pmForm.getNextDueDate() + "'," + "asset_pm.Last_Complied_Value="
						+ pmForm.getLastCompiledValue() + "," + "asset_pm.Next_Due_Value=" + pmForm.getNextDueValue()
						+ "" + ", asset_pm.compliance_status='" + pmForm.getComplianceStatus() + "'"
						+ " where asset_pm.Record_ID='" + emmsDataForm.getRecordId() + "'"
						+ " and asset_pm.Record_Row_ID='" + pmForm.getRecordRowId() + "'";
			}
			if (pmForm.getLastCompiledDate().length() > 0) {
				sql = "update asset_pm set asset_pm.Last_Complied_Date='" + pmForm.getLastCompiledDate() + "',"
						+ "asset_pm.Last_Complied_Value=" + pmForm.getLastCompiledValue() + ","
						+ "asset_pm.Next_Due_Value=" + pmForm.getNextDueValue() + "" + ", asset_pm.compliance_status='"
						+ pmForm.getComplianceStatus() + "'" + " where asset_pm.Record_ID='"
						+ emmsDataForm.getRecordId() + "'" + " and asset_pm.Record_Row_ID='" + pmForm.getRecordRowId()
						+ "'";
			}
			return jdbcTemplate.update(sql);
		} else
			return 0;
	}

	public int updateTableOrder(TableStatusForm tableStatusForm, String tableName) {
		String sql = "update record_id_details set Type='EXPORT',Table_Order='" + tableStatusForm.getTableOrder()
				+ "' where table_name='" + tableName + "'";

		return jdbcTemplate.update(sql);
	}

	public int updateStatus(String installableStatus, String recordStatus, String recordId) {
		System.out.println("inside updateStatus=" + installableStatus + recordId + recordStatus);
		String sql = "update asset_details set asset_details.Installable_Status='" + installableStatus + "',"
				+ "asset_details.Record_Status='" + recordStatus + "'" + " where asset_details.Record_ID='" + recordId
				+ "'";
		return jdbcTemplate.update(sql);

	}

	public int updateFreeze(boolean freeze, String recordId) {

		String sql = "update asset_details set asset_details.Freeze='" + 1

				+ "'" + " where asset_details.Record_ID='" + recordId + "'";
		return jdbcTemplate.update(sql);
	}

	public int updatetableOrder() {

		String sql = "update record_id_details set Table_Order='5'";
		return jdbcTemplate.update(sql);
	}

	public int updateMeter(AssetConfigForm assetConfigForm, int flag) {
		String installedPn;

		if (flag == 0) {
			installedPn = assetConfigForm.getInstalledPN();

		} else {
			installedPn = assetConfigForm.getInLieuPn();
		}
		System.out.println("FreezeUpdateMeter:" + installedPn + ":" + flag);
		String sql = "update asset_meter_actual,asset_cfg_actual set asset_meter_actual.Install_CM_Item='" + installedPn
				+ "', asset_meter_actual.Install_Serial_Num='" + assetConfigForm.getInstalledSN() + "'"
				+ " where asset_meter_actual.Existing_Asset_Num='" + assetConfigForm.getAssetNum() + "'"
				+ "OR asset_cfg_actual.build_item=asset_meter_actual.build_item and asset_cfg_actual.lcn=asset_meter_actual.lcn "
				+ "and asset_meter_actual.CM_Item='" + installedPn + "'";

		System.out.println(sql);
		return jdbcTemplate.update(sql);
	}

	public void updateMeter(InstallableAssetForm installableForm, int flag) {
		String installedPn;

		if (flag == 0) {
			installedPn = installableForm.getInstallablePN();

		} else {
			installedPn = installableForm.getiNlieuPN();
		}
		System.out.println("FreezeUpdateMeter:" + installedPn + ":" + flag);
		String sql = "update asset_meter_actual,asset_cfg_actual set asset_meter_actual.Install_CM_Item='" + installedPn
				+ "', asset_meter_actual.Install_Serial_Num='" + installableForm.getInstallableSN() + "'"
				+ " where asset_meter_actual.Existing_Asset_Num='" + installableForm.getAssertnum() + "'"
				+ "OR asset_cfg_actual.build_item=asset_meter_actual.build_item and asset_cfg_actual.lcn=asset_meter_actual.lcn "
				+ "and asset_meter_actual.CM_Item='" + installedPn + "'";

		System.out.println(sql);
		try {
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int[] importXml(String[] queries) {

		return jdbcTemplate.batchUpdate(queries);
	}

	public static LinkedHashMap<String, String> getInstalledPn(String buildItem, String recordId) {
		String query = "select CM_Item from asset_model where record_id='" + recordId + "' and Build_Item='" + buildItem
				+ "'";

		List<Map<String, Object>> cmitems = jdbcTemplate.queryForList(query);
		LinkedHashMap<String, String> l = new LinkedHashMap<String, String>();
		for (Map<String, Object> cmitem : cmitems) {
			for (Iterator<Map.Entry<String, Object>> it = cmitem.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				System.out.println("key=" + key + " :" + "value=" + value);
				l.put(value.toString(), value.toString());
			}
		}

		return l;

	}

	public boolean isRecordIdPresent(String recordId) {

		String sql = "select count(record_id) from asset_cfg_actual where record_id=?";
		int count = this.jdbcTemplate.queryForObject(sql, new Object[] { recordId }, Integer.class);
		if (count > 0) {
			return true;
		}

		return false;

	}

	public List<String> getLcnIndicator() {
		String sql = "select lcn from asset_cfg_actual where indicator='S'";
		List<String> lcnList = this.jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("LCN");
			}
		});
		System.out.println("indicator lcn=" + lcnList);

		return lcnList;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getLcnedit() throws SQLException {
		Map<String, String> child = new HashMap<String, String>();

		String sql = "select lcn from asset_cfg_actual,asset_details where asset_details.freeze='0'";
		List<String> lcnedit = this.jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("LCN");
			}
		});

		for (String s : lcnedit) {

			int length = s.length();
			if (length == 1) {

				child.put(s, s);

			} else if (length == 5) {

				child.put(s, s.substring(0, 1));

			} else {

				child.put(s, s.substring(0, length - 4));

			}
		}

		ArrayList<String> valueList = new ArrayList<String>(child.values());
		InstallableAssetForm installableForm = new InstallableAssetForm();
		for (String k : lcnedit) {
			if (lcnedit.contains(valueList)) {
				installableForm.setInstallablePN("");
				installableForm.setiNlieuPN("");
				installableForm.setInstallableSN("");
				installableForm.setConditionCode("");
			}
		}
		return child;
	}

	public Map<String, String> getLcnList(String recordId) {
		Map<String, String> parentChild = new HashMap<String, String>();

		String sql = "select lcn from asset_cfg_actual where Record_ID='" + recordId + "'";
		List<String> lcns = this.jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("LCN");
			}
		});

		for (String s : lcns) {
			int length = s.length();
			if (length == 1) {

				parentChild.put(s, s);
			} else if (length == 5) {

				parentChild.put(s, s.substring(0, 1));
			} else {

				parentChild.put(s, s.substring(0, length - 4));

			}

		}

		System.out.println("HalDao:LCNS=" + lcns);
		System.out.println("HalDao:parentchild:" + parentChild);

		return parentChild;
	}

	public int insertRegisteredUser(UserRegistrationForm user) {
		String sql = "select count(*) from users where username = ? ";
		int userCount = this.jdbcTemplate.queryForObject(sql, Integer.class, user.getServiceNo());
		if (userCount == 0) {
			sql = "insert into users (username, password, enabled, firstName, lastName) values(?, ?, ?, ?, ?)";
			int status = jdbcTemplate.update(sql, user.getServiceNo(), user.getPassword(), 1, user.getFirstName(),
					user.getLastName());
			System.out.println("status: " + status);
			if (status == 1) {
				sql = "insert into authorities (username, authority) values(?, ?)";
				status = jdbcTemplate.update(sql, user.getServiceNo(), user.getRole());
			}
			return status;
		} else
			return 0;
	}

	public static String getUomValue(String value, String uomType) {
		// String a[]=value.split(".0");
		if (uomType.equalsIgnoreCase("hh:mm:ss")) {
			String decimalValue = "";
			for (int i = 0; i < value.length(); i++) {
				if (value.charAt(i) == '.') {
					break;
				}
				decimalValue = decimalValue + value.charAt(i);

			}

			Long l = Long.parseLong(decimalValue);

			int s = (int) (l % 60);
			int h = (int) (l / 60);
			int m = h % 60;
			h = h / 60;

			String hh = String.valueOf(h);
			String mm = String.valueOf(m);
			String ss = String.valueOf(s);

			if (hh.length() < 2) {

				hh = "0" + hh;
			}
			if (mm.length() < 2) {

				mm = "0" + mm;
			}
			if (ss.length() < 2) {

				ss = "0" + ss;
			}

			String uom = hh + ":" + mm + ":" + ss;

			return uom;
		} else {
			String decimalValue = "";
			for (int i = 0; i < value.length(); i++) {
				if (value.charAt(i) == '.') {
					break;
				}
				decimalValue = decimalValue + value.charAt(i);

			}

			Long l = Long.parseLong(decimalValue);

			int h = (int) (l / 60);
			int m = h % 60;
			h = h / 60;

			String hh = String.valueOf(h);
			String mm = String.valueOf(m);

			if (hh.length() < 2) {

				hh = "0" + hh;
			}
			if (mm.length() < 2) {

				mm = "0" + mm;
			}

			String uom = hh + ":" + mm;

			return uom;
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> fetchPmValues(String installedPN, String installedSN, String buildItem) {
		System.out.println("pmValues=" + buildItem + ":" + installedPN + ":" + installedSN);
		String sql = "select last_complied_value,last_complied_date from asset_pm where build_item='" + buildItem
				+ "' and Install_CM_Item='" + installedPN + "'" + "and install_serial_num='" + installedSN + "' ";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map>() {

			@Override
			public Map extractData(ResultSet rs) throws SQLException, DataAccessException {
				HashMap<String, String> mapRet = new HashMap<String, String>();
				while (rs.next()) {
					mapRet.put("lastCompiledValue", rs.getString("last_complied_value"));
					mapRet.put("lastCompiledDate", rs.getString("last_complied_date"));
				}
				return mapRet;
			}

		});
	}

	public void insertPostFlight(FlbPostFlightDataForm postFlightDataForm) {
		String query = "insert into flb_post_flt_details(Record_ID,FLB_NUM,FLT_NUM,FLT_DATE,Departure_Time,Arrival_Time,Flt_Hrs,Flight_type,Flt_status,Creation_Date,record_Row_Id) values('"
				+ postFlightDataForm.getRecordId() + "','" + postFlightDataForm.getFlbNo() + "','"
				+ postFlightDataForm.getFlightNumber() + "'," + postFlightDataForm.getFlightDate() + ","
				+ postFlightDataForm.getDepartureTime() + "," + postFlightDataForm.getArrivalTime() + ","
				+ postFlightDataForm.getFlightHours() + ",'" + postFlightDataForm.getFlightType() + "','"
				+ postFlightDataForm.getStatus() + "','" + postFlightDataForm.getCreationDate() + "','"
				+ postFlightDataForm.getRecordRowId() + "')";
		System.out.println("PostFlight Insert Query=" + query);
		try {
			jdbcTemplate.execute(query);
		} catch (Exception e) {
			System.out.println("Adding Post Flight Row Exception=" + e.getMessage());
		}
	}

	// Sorite AR DB insert
	public void insertSortieArRow(FlbSortieArForm newSortieRow) {
		String query = "INSERT INTO flb_sortie_accept" + "(Record_ID, FLB_Num, Sortie_Num, Sortie_Date, ETD_IST, "
				+ "Etd_Date, Duration, Flight_Type, Sortie_Status, "
				+ "Sortie_Status_Date, Change_By, Caution_Remarks, "
				+ "Reason, Record_Row_ID, Error, Error_Description)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("Query prepared for sorite AR" + query);
		int status = jdbcTemplate.update(query, newSortieRow.getRecordId(), newSortieRow.getFlbNum(),
				newSortieRow.getSortieNo(), newSortieRow.getSortieDate(), newSortieRow.getETD(),
				newSortieRow.getEtdDate(), newSortieRow.getDuration(), newSortieRow.getFlightType(),
				newSortieRow.getSortieStatus(), newSortieRow.getStatusDate(), newSortieRow.getChangedBy(),
				newSortieRow.getRemarks(), newSortieRow.getReason(), newSortieRow.getRecordRowId(),
				newSortieRow.getError(), newSortieRow.getErrordesc());
		if (status == 1) {
			System.out.println("Data inserted in Sortie ar table");
		} else {
			System.out.println("Data not inserted in Sortie ar table");
		}

		jdbcTemplate.execute(query);
	}

	// Sorite data update
	public void updateSortieArData(FlbSortieArForm flbSortieRow) {
		String query = "UPDATE flb_sortie_accept SET " + "Sortie_Date = ?, " + "Etd_Date = ?, " + "ETD_IST = ?, "
				+ "Duration = ?, " + "Flight_Type = ?, " + "Caution_Remarks = ?, " + "Sortie_Status = ?, "
				+ "Reason = ?, " + "Error = ?, " + "Error_Description = ? "
				+ "WHERE (Record_ID = ?) and (Record_Row_ID = ?);";
		System.out.println(flbSortieRow.toString());
		int status = jdbcTemplate.update(query, flbSortieRow.getSortieDate(), flbSortieRow.getEtdDate(),
				flbSortieRow.getETD(), flbSortieRow.getDuration(), flbSortieRow.getFlightType(),
				flbSortieRow.getRemarks(), flbSortieRow.getSortieStatus(), flbSortieRow.getReason(),
				flbSortieRow.getError(), flbSortieRow.getErrordesc(), flbSortieRow.getRecordId(),
				flbSortieRow.getRecordRowId());
		if (status == 1) {
			System.out.println("Data updated in sortie ar table of : " + flbSortieRow.getRecordId());
		} else {
			System.out.println("Data not updated in DB");
		}
	}

	public void updatePostFlight(FlbPostFlightDataForm postFlightDataForm) {
		String query = "UPDATE flb_post_flt_details SET " + "Flight_type = ?, " + "FLT_DATE = ?, "
				+ "Departure_Time = ?, " + "Arrival_Time = ?, " + "Flt_status = ?, " + "Flt_Hrs = ?," + "error=?, "
				+ "Sortie_Num=? " + "WHERE (Record_Row_ID = ?);";

		if (null != postFlightDataForm.getFlightDate() && postFlightDataForm.getFlightDate().isEmpty()) {

			postFlightDataForm.setFlightDate(null);
		}
		int status = jdbcTemplate.update(query, postFlightDataForm.getFlightType(), postFlightDataForm.getFlightDate(),
				postFlightDataForm.getDepartureTime(), postFlightDataForm.getArrivalTime(),
				postFlightDataForm.getStatus(), postFlightDataForm.getFlightHours(), postFlightDataForm.getError(),
				postFlightDataForm.getSortieNumber(), postFlightDataForm.getRecordRowId());
		if (status == 1) {
			System.out.println("Data updated in postFlight table of : " + postFlightDataForm.getRecordRowId());
		} else {
			System.out.println("Data not updated in DB");
		}
	}

	public static List<String> getFlightTypes(String recordId) {
		String query = "select distinct flight_type from flb_post_flt_details where recordId='" + recordId + "'";
		List<String> flightTypes = new ArrayList<>();
		flightTypes.add("Economy");
		flightTypes.add("Business");
		return flightTypes;

	}

	public static List<String> getSortieNumbers(String recordId) {
		String query = "SELECT sortie_num FROM flb_sortie_accept  where Sortie_Status='ACCEPTED' and record_Id='"
				+ recordId + "'";

		// List<String> sortieNumbers = new ArrayList<>();
		List<String> sortieNumbers = jdbcTemplate.query(query, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("sortie_num");
			}
		});
		System.out.println("SortieNumbers=" + sortieNumbers);
		return sortieNumbers;

	}

	public void updateSortieStatus(FlbPostFlightDataForm postFlightRow) {
		String query = "update flb_sortie_accept set Sortie_Status='CLOSED' where Sortie_Num='"
				+ postFlightRow.getSortieNumber() + "'";
		System.out.println("SortieStatusUpdateQuery=" + query);
		try {
			jdbcTemplate.update(query);
		} catch (Exception e) {
			System.out.println("updateSortieStatusExceptioCatch=" + e.getMessage());
		}
	}

	public String getEtdDate(String sortieNo) {
		String query = "select Etd_Date from flb_sortie_accept where Sortie_Num=?";
		String etdDate = this.jdbcTemplate.queryForObject(query, new Object[] { sortieNo }, String.class);
		System.out.println("EtdDATE=" + etdDate);
		return convertor.getDate2(etdDate);
	}

}
