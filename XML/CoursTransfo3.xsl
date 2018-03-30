<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml"/>
<xsl:template match="/">
	<xsl:apply-templates select="//MODULE" />
</xsl:template>
<xsl:template match="*">
	<xsl:copy>
		<xsl:copy-of select="@*"/>
		<xsl:if test="name()='MODULE'">
			<xsl:element name="OPTION"> à venir...</xsl:element>
		</xsl:if>
		<xsl:apply-templates/>
	</xsl:copy>
</xsl:template> 
</xsl:stylesheet>
