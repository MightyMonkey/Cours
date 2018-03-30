<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml"/>

<xsl:template match="/">
	<xsl:apply-templates select="/*/*"/>
</xsl:template>

<xsl:template match="/*/*">
	<xsl:copy>
		<xsl:apply-templates select="@INTITULE"/>
		<xsl:apply-templates/>
	</xsl:copy>

</xsl:template>
 
<xsl:template match="@INTITULE">
	<xsl:element name="{name()}">
		<xsl:value-of select="."/>
	</xsl:element>
</xsl:template> 

<xsl:template match="text()| @*">
	
</xsl:template> 


</xsl:stylesheet>
