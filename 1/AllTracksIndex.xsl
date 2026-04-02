<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <head>
        <title>All Tracks Index</title>
      </head>
      <body>
        <h1>All Tracks Index</h1>
        <ul>
          <xsl:for-each select="//track">
            <li>
              <a>
                <xsl:attribute name="href">
                  <xsl:value-of select="ancestor::album/name"/>-Track-<xsl:value-of select="@id"/>.mp3
                </xsl:attribute>
                <xsl:value-of select="name"/>
              </a>
            </li>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
