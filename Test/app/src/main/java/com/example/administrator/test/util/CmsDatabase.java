package com.example.administrator.test.util;

public class CmsDatabase {
   public static   WebDbConfig m_syswdbc=new WebDbConfig();
   public static void InitialWebDbEnvironment(){
	 
	   m_syswdbc.BASE_URL=WebDbConfig.m_BASE_URL;
	   m_syswdbc.loginMethod=WebDbConfig.m_loginMethod;
	   m_syswdbc.retrieveMethod=WebDbConfig.m_retrieveMethod;
	   m_syswdbc.saveMethod=WebDbConfig.m_saveMethod;
	   m_syswdbc.sysUserID=WebDbConfig.m_sysUserID;
	   m_syswdbc.sysUserPass=WebDbConfig.m_sysUserPass;
   }
}
