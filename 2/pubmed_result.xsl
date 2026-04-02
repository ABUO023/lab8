<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <head>
        <title>PubMed Results Index</title>
      </head>
      <body>
        <h1>PubMed Results</h1>
        <ul>
          <xsl:for-each select="//PubmedArticle[MedlineCitation/Article/@PubModel='Electronic']">
            <li>
              <xsl:variable name="lastName" select="MedlineCitation/Article/AuthorList/Author[1]/LastName"/>
              <xsl:variable name="firstName" select="MedlineCitation/Article/AuthorList/Author[1]/ForeName"/>
              <xsl:variable name="title" select="MedlineCitation/Article/ArticleTitle"/>
              <xsl:variable name="doi" select="PubmedData/ArticleIdList/ArticleId[@IdType='doi']/text()"/>
              
              <strong><xsl:value-of select="$lastName"/>, <xsl:value-of select="$firstName"/></strong>
              <br/>
              <xsl:value-of select="$title"/>
              <br/>
              <a>
                <xsl:attribute name="href">http://dx.doi.org/<xsl:value-of select="$doi"/></xsl:attribute>
                http://dx.doi.org/<xsl:value-of select="$doi"/>
              </a>
              <br/><br/>
            </li>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
