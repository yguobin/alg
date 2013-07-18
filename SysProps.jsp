<%!
	private static final String JAVA_VER = "java.version";
%>


<p>Java version: <%= System.getProperty(JAVA_VER) %></p>

<%-- User program set override system properties! -->
<% System.setProperties(new java.util.Properties()); %>

<p>Java version: <%= System.getProperty(JAVA_VER) %></p>
