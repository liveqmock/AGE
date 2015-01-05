
function onLoad_cab() {
/*	var s = '';
	if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
		if (window.navigator.cpuClass == "x86") {
			s = "<object id=\"CryptoAgent\" codebase=\""+_ctx+"/scripts/ca/CryptoKit.CertEnrollment.Pro.x86.cab\" classid=\"clsid:71BB5253-EF2B-4C5B-85FF-1FD6A03C29A6\" ></object>";
		}else {
			s = "<object id=\"CryptoAgent\" codebase=\""+_ctx+"/scripts/ca/CryptoKit.CertEnrollment.Pro.x64.cab\" classid=\"clsid:9E7B8F05-ADBE-4067-ABC6-28E0230A5C18\" ></object>";
		}
	}
	else {
		s = "<embed id=\"CryptoAgent\" type=\"application/npCryptoKit.CertEnrollment.Pro.x86\" style=\"height: 0px; width: 0px\">";
	}
	$("body").append(s);
	*/
	
	try {
        var eDiv = document.createElement("div");
        if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
            if (window.navigator.cpuClass == "x86") {
                eDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\""+_ctx+"/scripts/ca/CryptoKit.Ultimate.x86.cab\" classid=\"clsid:4C588282-7792-4E16-93CB-9744402E4E98\" ></object>";
            }
            else {
                eDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\""+_ctx+"/scripts/ca/CryptoKit.Ultimate.x64.cab\" classid=\"clsid:B2F2D4D4-D808-43B3-B355-B671C0DE15D4\" ></object>";
            }
        }
        else {
            eDiv.innerHTML = "<embed id=\"CryptoAgent\" type=\"application/npCryptoKit.Ultimate.x86\" style=\"height: 0px; width: 0px\">";
        }
        document.body.appendChild(eDiv);
    }
    catch (e) {
        alert(e);
        return;
    }
}

$(document).ready(function() {
	onLoad_cab();
});

function GetAllCertInfo() {
	var CryptoAgent = document.getElementById("CryptoAgent");
	try 
	{
		var certInfo = CryptoAgent.CFCA_GetAllCertInfo();
		if (!certInfo) {
			var errorDesc = CryptoAgent.GetLastErrorDesc();
			alert(errorDesc);
			return;
		}
		return certInfo; 
	}
	catch (e)
	{
		var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
		alert(e+": "+LastErrorDesc);
	}
}
//Select certificate
function SelectCertificateOnClick() {
	var bSelectCertResult = false;
	try {                  
		var subjectDNFilter = "";
		var issuerDNFilter = "";
		var serialNumFilter = "";
//		subjectDNFilter = document.getElementById("SubjectDNFilter").value;
//		issuerDNFilter = document.getElementById("IssuerDNFilter").value;
//		serialNumFilter = document.getElementById("SerialNumFilter").value;

		bSelectCertResult = CryptoAgent.SelectCertificate(subjectDNFilter, issuerDNFilter, serialNumFilter);                     
		// Opera浏览器，NPAPI函数执行结果为false时，不能触发异常，需要自己判断返回值。
		if (!bSelectCertResult){
			var errorDesc = CryptoAgent.GetLastErrorDesc();
			alert(errorDesc);
			return bSelectCertResult;
		}
		return bSelectCertResult;
	}
	catch (e) {
		var errorDesc = CryptoAgent.GetLastErrorDesc();
		alert(errorDesc);
		return bSelectCertResult;
	}
}
//Get certificate information
function GetCertInfoOnClick() {
	try {
		var obj = new Object();
		var SubjectDN= "SubjectDN" ;
		var SubjectCN= "SubjectCN";
		var IssuerDN= "IssuerDN";
		var SerialNumber= "SerialNumber";
		var CSPName= "CSPName";
		var CertType= "CertType";
		obj.name = CryptoAgent.GetSignCertInfo(SubjectCN).split('@')[1];
		obj.passward = CryptoAgent.GetSignCertInfo(SubjectCN);
		obj.serialNumber = CryptoAgent.GetSignCertInfo(SerialNumber);
		obj.info = CryptoAgent.GetSignCertInfo(SerialNumber);
		// Opera浏览器，NPAPI函数执行结果为false时，不能触发异常，需要自己判断返回值。
		if (!obj.name) {
			var errorDesc = CryptoAgent.GetLastErrorDesc();
			alert(errorDesc);
			return null;
		}
		return obj;
	} catch (e) {
		var errorDesc = CryptoAgent.GetLastErrorDesc();
		alert(errorDesc);
		return null;
	}
}
function sign(source) {
	try {
		var signature = "";
		var signType = "";
		var selectedAlg = "SHA-1";
		signType = "Attach";
		if ("Attach" == signType) {
			// PKCS#7 Attach
			signature = CryptoAgent.SignMsgPKCS7(source, selectedAlg, true);
		}

		if (!signature) {
			var errorDesc = CryptoAgent.GetLastErrorDesc();
			alert(errorDesc);
			return null;
		}
		return signature;
	} catch (e) {
		var errorDesc = CryptoAgent.GetLastErrorDesc();
		alert(errorDesc);
		return null;
	}
}