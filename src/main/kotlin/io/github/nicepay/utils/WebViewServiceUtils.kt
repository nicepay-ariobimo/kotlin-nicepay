package io.github.nicepay.utils

import com.sun.net.httpserver.HttpServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Desktop
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.net.InetSocketAddress
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class WebViewServiceUtils {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger("[Web View Service]")


        fun openHtmlInBrowser(htmlContent: String?) {
            if (htmlContent == null || htmlContent.isEmpty()) {
                LOGGER.info(LoggerPrint.LOG_ERROR, "HTML content is null or empty.")
                return
            }

            try {
                val encodedHtml = URLEncoder.encode(htmlContent, StandardCharsets.UTF_8)
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E", "~")
                    .replace("%21", "!")
                    .replace("%27", "'")
                    .replace("%28", "(")
                    .replace("%29", ")")
                    .replace("%2C", ",")
                    .replace("%2F", "/")

                // Create data URI
                val dataUri = "data:text/html;charset=utf-8,$encodedHtml"
                val data = URI(dataUri)
                LOGGER.info("uri : {}", dataUri)

                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(data)
                } else {
                    LOGGER.info(LoggerPrint.LOG_ERROR, "Desktop or browse action not supported.")
                }
            } catch (e: IOException) {
                LOGGER.info(LoggerPrint.LOG_ERROR, "IOException occurred: " + e.message)
            } catch (e: Exception) {
                LOGGER.info(LoggerPrint.LOG_ERROR, "Exception occurred: " + e.message)
            }
        }

        fun serveHtml(htmlContent: String, baseUrl: String, txId: String) {
            try {
                // Parse the base URL to extract the host and port
                val uri = URI("$baseUrl/$txId")
                val host = uri.host
                val port = if (uri.port == -1) (if (uri.scheme == "https") 443 else 80) else uri.port
                val protocol = uri.scheme

                // Create and start the HTTP server
                val address = InetSocketAddress(host, port)
                val server = HttpServer.create(address, 0)
                server.createContext("/$txId") { exchange ->
                    exchange.sendResponseHeaders(200, htmlContent.toByteArray().size.toLong())
                    val os = exchange.responseBody
                    os.write(htmlContent.toByteArray())
                    os.close()
                }
                server.executor = null
                server.start()
                // Open the URL in the default browser
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(uri)
                } else {
                    println("Desktop or browse action not supported.")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @Throws(IOException::class)
        fun openHtmlEwalletInBrowser(resClient: String, tXid: String) {
            val script : String = "\n<script>\n" +
                    "setInterval(function () {\n" +
                    "console.log(document.getElementById('returnForm_ewallet').action)\n" +
                    "window.location.href=document.getElementById('returnForm_ewallet').action\n" +
                    "}, 5000);\n" +
                    "</script>"
            openHtmlInBrowser(resClient + script, tXid)
        }

        @Throws(IOException::class)
        fun openHtmlInBrowser(resClient: String, tXid: String) {
            //Save the HTML content to a temporary file

            val tempFile = File.createTempFile("Payment-$tXid-", ".html")
            val writer = FileWriter(tempFile)
            writer.write(resClient)
            writer.close()

            // Open page on browser
            Desktop.getDesktop().browse(tempFile.toURI())
        }
    }

}