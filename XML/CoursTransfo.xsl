<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" />

<xsl:template match="/">
	<xsl:apply-templates select="ENSEIGNEMENT/COURS"/>
</xsl:template>

<xsl:template match="COURS"> 
	<xsl:text> -------------------------------------------------</xsl:text>   <br/>
	<xsl:value-of select="@INTITULE"/>  <br/>
	<xsl:text> Salle: </xsl:text>  <xsl:value-of select="SALLE"/>  <br/>
	<xsl:text> Modules : </xsl:text>   <xsl:apply-templates select="MODULE"/>  <br/>
	<xsl:text> Inscrits (note):  </xsl:text>  <br/>
	<xsl:for-each select="ETUDIANT">
		<xsl:if test="NOTE"> <xsl:value-of select="@NUMERO"/> (<xsl:value-of select="NOTE"/>)  <br/> </xsl:if> 
	</xsl:for-each>
</xsl:template>

<xsl:template match="MODULE">
	<xsl:value-of select="@INTITULE"/> 
	(<xsl:for-each select="ENSEIGNANT">
		<xsl:value-of select="."/>
	</xsl:for-each>)
	<xsl:if test="position() &lt; last()">   <xsl:text>   ,   </xsl:text>  
	</xsl:if>  
</xsl:template>

</xsl:stylesheet>
