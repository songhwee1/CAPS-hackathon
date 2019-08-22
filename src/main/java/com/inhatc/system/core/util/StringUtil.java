/*package com.inhatc.system.core.util;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil extends StringUtils {
   Logger logger = LoggerFactory.getLogger(this.getClass());

   public static String nvl(Object o) {
      return o == null ? "" : o.toString();
   }

   public static boolean isEmpty(String str) {
      return str == null || str.trim().length() == 0;
   }

   public static String isEmpty(Object str, String replaceStr) {
      return isEmpty(str) ? replaceStr : (String) str;
   }

   public static boolean isEmpty(Object str) {
      return str == null || toString(str).trim().length() == 0;
   }

   public static String toString(Object src) {
      String rst = "";

      try {
         rst = String.valueOf(src);
         return rst;
      } catch (Exception arg2) {
         return "";
      }
   }

   public static String[] splitVal(String str, String separator) {
      StringTokenizer st = new StringTokenizer(str, separator);
      String[] values = new String[st.countTokens()];

      for (int pos = 0; st.hasMoreTokens(); values[pos++] = st.nextToken()) {
         ;
      }

      return values;
   }

   public static String nullToString(String sString, String sDefault) {
      return isEmpty(sString) ? sDefault : sString;
   }

   public static String nullToString(String sString) {
      return nullToString(sString, "");
   }

   public static String lpad(String sString, int nTotalLen, String sIn) {
      StringBuffer sReturn = new StringBuffer(sString.trim());
      int nLen = nTotalLen - sReturn.length();

      for (int i = 0; i < nLen; ++i) {
         String tmp = sIn + sReturn.toString();
         sReturn = new StringBuffer(tmp);
      }

      return sReturn.toString();
   }

   public static String lpad(int sString, int nLen, String sIn) {
      return lpad(Integer.toString(sString), nLen, sIn);
   }

   public static String getMaxLengthMore(String sStr, int nLen) {
      String sReturnStr = sStr;
      String sOneChar = "";
      Object bOne = null;
      int nCount = 0;

      try {
         for (int e = 0; e < sStr.length(); ++e) {
            sOneChar = (new Character(sStr.charAt(e))).toString();
            byte[] arg7 = sOneChar.getBytes();
            nCount += arg7.length;
            if (nCount > nLen) {
               sReturnStr = sStr.substring(0, e) + "...";
               break;
            }
         }
      } catch (Exception arg6) {
         sReturnStr = sStr;
      }

      sReturnStr = sReturnStr.replace('\n', ' ');
      sReturnStr = sReturnStr.replace('\r', ' ');
      return sReturnStr;
   }

   public static int parseInt(Object obj) {
      try {
         return obj == null ? 0 : Integer.parseInt(String.valueOf(obj));
      } catch (NumberFormatException arg1) {
         return 0;
      }
   }

   public static long parseLong(Object obj) {
      try {
         return obj == null ? 0L : Long.parseLong(String.valueOf(obj));
      } catch (NumberFormatException arg1) {
         return 0L;
      }
   }

   public static String format(long n) {
      NumberFormat nf = NumberFormat.getNumberInstance();

      try {
         return nf.format(n);
      } catch (Exception arg3) {
         return "0";
      }
   }

   public static String format(Object n) {
      NumberFormat nf = NumberFormat.getNumberInstance();

      try {
         if (isEmpty(n)) {
            return "0";
         } else {
            double e = Double.parseDouble((String) n);
            return nf.format(e);
         }
      } catch (Exception arg3) {
         return "0";
      }
   }

   public static String decimalFormat(Object n, String format) {
      String foramtStr = format;

      try {
         if (isEmpty(n)) {
            return "0";
         } else {
            if (format.equals(".0")) {
               foramtStr = "0.0";
            }

            double e = Double.parseDouble((String) n);
            DecimalFormat df = new DecimalFormat(foramtStr);
            return df.format(e);
         }
      } catch (Exception arg5) {
         return "0";
      }
   }

   public static String decimalFormat(Object n) {
      try {
         if (isEmpty(n)) {
            return "0";
         } else {
            double e = Double.parseDouble((String) n);
            DecimalFormat df = new DecimalFormat(".0");
            return df.format(e);
         }
      } catch (Exception arg3) {
         return "0";
      }
   }

   public static String formatting(Object source, String format) {
      if (isEmpty(source)) {
         return "";
      } else {
         String sourceStr = String.valueOf(source);
         String[] args = removeEmptyString(format.split("[^#0]"));
         int[] argsLen = new int[args.length];
         int idx = 0;
         String[] delims = args;
         int head = args.length;

         int curIdx;
         for (curIdx = 0; curIdx < head; ++curIdx) {
            String loopRst = delims[curIdx];
            argsLen[idx] = loopRst.length();
            ++idx;
         }

         String[] arg14 = new String[args.length];
         curIdx = 0;

         for (head = 0; head < args.length; ++head) {
            String arg16 = args[head];
            String[] loopSize = new String[]{formatting(sourceStr, curIdx, argsLen[head], arg16)};
            System.arraycopy(loopSize, 0, arg14, head, loopSize.length);
            curIdx += argsLen[head];
         }

         String arg15 = format.substring(0, 1);
         delims = removeEmptyString(format.split("[#0]"));
         int arg17 = delims.length > arg14.length ? delims.length : arg14.length;
         ArrayList rstList = new ArrayList();
         int rst;
         if (!arg15.equals("0") && !arg15.equals("#")) {
            for (rst = 0; rst < arg17; ++rst) {
               if (rst < delims.length) {
                  rstList.add(delims[rst]);
               }

               if (rst < arg14.length) {
                  rstList.add(arg14[rst]);
               }
            }
         } else {
            for (rst = 0; rst < arg17; ++rst) {
               if (rst < arg14.length) {
                  rstList.add(arg14[rst]);
               }

               if (rst < delims.length) {
                  rstList.add(delims[rst]);
               }
            }
         }

         StringBuffer arg18 = new StringBuffer("");
         Iterator arg13 = rstList.iterator();

         while (arg13.hasNext()) {
            String tmp = (String) arg13.next();
            arg18.append(tmp);
         }

         return arg18.toString();
      }
   }

   public static String[] removeEmptyString(String[] args) {
      int i = 0;
      String[] arg4 = args;
      int arg3 = args.length;

      for (int tmp = 0; tmp < arg3; ++tmp) {
         String rst = arg4[tmp];
         if (isEmpty(rst)) {
            ++i;
         }
      }

      String[] arg6 = new String[i];
      i = 0;
      String[] arg5 = args;
      int arg8 = args.length;

      for (arg3 = 0; arg3 < arg8; ++arg3) {
         String arg7 = arg5[arg3];
         if (isEmpty(arg7)) {
         
         }
         else {
        	 arg6[i] = arg7;
             ++i;
         }
      }

      return arg6;
   }

   public static String formatting(String param, int start, int range, String format) {
      String substr = substringMatch(param, start, start + range);
      substr = substringMatch(substr, 0, format.length());
      if (substr.length() < format.length()) {
         String tmp = format.substring(0, format.length() - substr.length()) + substr;
    	  String tmp ="";
    	  if(substr != null) {
    	  tmp = format.substring(0, format.length() - substr.length()) + substr;
    	  }
    	  else { // error process
    	  }
         substr = tmp;
      }

      return substr.replaceAll("#", "");
   }

   private static String substringMatch(String substr, int i, int length) {
	// TODO Auto-generated method stub
	return null;
}

public static boolean isEquals(String a, String b) {
      return a == null ? false : (b == null ? false : a.equals(b));
   }

   public static String isEquals(String a, String b, String success, String fail) {
      return isEquals(a, b) ? success : fail;
   }

   public static String[] explode(String srcParam, String delimParam) {
      String src = srcParam;
      String delim = delimParam;
      if (srcParam != null && srcParam.length() != 0) {
         if (delimParam.length() >= 2) {
            src = change(srcParam, delimParam, " ");
            delim = " ";
         }

         StringTokenizer stk = new StringTokenizer(src, delim);
         int size = stk.countTokens();
         String[] rst = new String[size];

         for (int i = 0; stk.hasMoreTokens(); ++i) {
            rst[i] = stk.nextToken();
         }

         return rst;
      } else {
         return new String[0];
      }
   }

   public static String implode(String[] src, String delim) {
      if (src != null && src.length != 0) {
         int size = src.length;
         StringBuffer rst = new StringBuffer("");

         for (int i = 0; i < size; ++i) {
            if (i != size - 1) {
               String tmp = src[i] + delim;
               rst.append(tmp);
            } else {
               rst.append(src[i]);
            }
         }

         return rst.toString();
      } else {
         return "";
      }
   }

   public static String change(String source, String before, String after) {
      int i = 0;
      if (isEmpty(source)) {
         return "";
      } else {
         StringBuffer sb = new StringBuffer();

         for (int j = source.indexOf(before, i); j >= 0; j = source.indexOf(before, i)) {
            sb.append(source.substring(i, j));
            sb.append(after);
            i = j + before.length();
         }

         sb.append(source.substring(i));
         return sb.toString();
      }
   }

   public static String html(Object string) {
      if (string == null) {
         return null;
      } else {
         String rst = string.toString();
         rst = replace(rst, "<", "&#60;");
         rst = replace(rst, ">", "&#62;");
         rst = replace(rst, "\"", "&#34;");
         rst = replace(rst, "&", "&amp;");
         return rst;
      }
   }

   public static String objectToJSON(Object obj, String name) {
      String jsonData = "";
      ObjectMapper om = new ObjectMapper();
      LinkedHashMap lhm = new LinkedHashMap();
      lhm.put(name, obj);

      try {
         jsonData = om.writeValueAsString(lhm);
      } catch (Exception arg5) {
         ;
      }

      jsonData = jsonData.replace("<", "&#60;");
      jsonData = jsonData.replace(">", "&#62;");
      jsonData = jsonData.replace("\'", "&#39;");
      return nvl(jsonData);
   }

   public static String replaceByIdx(String src, String dest, int idx) {
      try {
         String e;
         if (src.length() == idx + 1) {
            e = src.substring(0, idx);
            return e + dest;
         } else if (idx != 0) {
            e = src.substring(0, idx);
            String suffix = src.substring(idx + 1);
            return e + dest + suffix;
         } else if (idx == 0) {
            e = src.substring(1);
            return dest + e;
         } else {
            return src;
         }
      } catch (Exception arg4) {
         return "";
      }
   }

   public static String getLeftMenuClassName(String currentIdx, String totalSize, String firstName, String lastName) {
      return parseInt(currentIdx) == 0
            ? firstName
            : (parseInt(currentIdx) == parseInt(totalSize) - 1 ? lastName : "");
   }

   public static String getLeftMenuIcon(List<Object> list, String open, String close) {
      return list != null && list.size() != 0 ? open : close;
   }

   public static String getHTMLCode(String text) {
      if (text != null && !text.equals("")) {
         StringBuffer sb = new StringBuffer(text);

         for (int i = 0; i < sb.length(); ++i) {
            char ch = sb.charAt(i);
            if (ch == 60) {
               sb.replace(i, i + 1, "&lt;");
               i += 3;
            } else if (ch == 62) {
               sb.replace(i, i + 1, "&gt;");
               i += 3;
            } else if (ch == 38) {
               sb.replace(i, i + 1, "&amp;");
               i += 4;
            } else if (ch == 39) {
               sb.replace(i, i + 1, "&#39;");
               i += 4;
            } else if (ch == 34) {
               sb.replace(i, i + 1, "&quot;");
               i += 5;
            } else if (ch == 32) {
               sb.replace(i, i + 1, "&nbsp;");
               i += 5;
            } else if (ch == 13 && sb.charAt(i + 1) == 10) {
               sb.replace(i, i + 2, "<BR>");
               i += 3;
            } else if (ch == 10) {
               sb.replace(i, i + 1, "<BR>");
               i += 3;
            }
         }

         return sb.toString();
      } else {
         return "";
      }
   }

   public static String substringByByteSize(String srcString, int limitSize, String appendString) {
      String tstring = srcString;
      int endindex = 0;
      int deltaIdx = 0;
      boolean ch = false;
      byte[] bytearr = srcString.getBytes();
      int len = bytearr.length;
      if (len > limitSize) {
         for (int ii = 0; ii < tstring.length(); ++ii) {
            if (deltaIdx >= limitSize) {
               endindex = ii;
               break;
            }

            char arg9 = tstring.charAt(ii);
            if (arg9 > 128) {
               deltaIdx += 2;
            } else {
               ++deltaIdx;
            }
         }

         if (endindex == 0) {
            endindex = tstring.length();
         }

         tstring = tstring.substring(0, endindex) + appendString;
      }

      return tstring;
   }

   public static String[] substringByByte(String[] rst, String srcString, int limitSize, String appendString) {
      String tstring = srcString;
      int endindex = 0;
      int deltaIdx = 0;
      boolean ch = false;
      byte[] bytearr = srcString.getBytes();
      int len = bytearr.length;
      if (len > limitSize) {
         for (int i = 0; i < rst.length; ++i) {
            for (int ii = 0; ii < tstring.length(); ++ii) {
               if (deltaIdx >= limitSize) {
                  endindex = ii;
                  break;
               }

               char arg11 = tstring.charAt(ii);
               if (arg11 > 128) {
                  deltaIdx += 2;
               } else {
                  ++deltaIdx;
               }
            }

            if (endindex == 0) {
               endindex = tstring.length();
            }

            rst[i] = tstring.substring(0, endindex);
            tstring = tstring.substring(endindex);
         }
      }

      return rst;
   }

   public static String secureString(String srcString) {
      String targetString = srcString;
      if (srcString != null && !srcString.isEmpty()) {
         targetString = srcString.replaceAll("\'", "&acute;");
         targetString = targetString.replaceAll("\"", "&quot;");
         targetString = targetString.replaceAll("<", "&lt;");
         targetString = targetString.replaceAll(">", "&gt;");
         targetString = targetString.replaceAll("\n", "<br>");
      }

      return targetString;
   }

   public static String convertUnicode(String text) {
      char[] chBuffer = text.toCharArray();
      StringBuffer buffer = new StringBuffer();

      for (int i = 0; i < chBuffer.length; ++i) {
         if (chBuffer[i] == 32) {
            buffer.append(" ");
         } else {
            buffer.append("\\u");
            buffer.append(Integer.toHexString(chBuffer[i]));
         }
      }

      return buffer.toString();
   }

   public static int arrayIndexOf(String[] src, String str) {
      if (src != null && src.length != 0) {
         if (str == null) {
            return -1;
         } else {
            int idx = 0;
            String[] arg5 = src;
            int arg4 = src.length;

            for (int arg3 = 0; arg3 < arg4; ++arg3) {
               String tmp = arg5[arg3];
               if (isEquals(tmp, str)) {
                  return idx;
               }

               ++idx;
            }

            return -1;
         }
      } else {
         return -1;
      }
   }

   public static boolean isInt(String target) {
      return isEmpty(target) ? false : Pattern.matches("^\\d*$", target.replaceAll(".0", ""));
   }

   public static boolean isEmpty(List<?> listTarget) {
      return listTarget == null || listTarget.isEmpty();
   }

   public static String nullToEmptyString(String srcStr) {
      return srcStr != null && !"null".equals(srcStr) && srcStr.length() != 0 ? srcStr : "";
   }

   public static String zeroToEmptyString(String srcStr) {
      return srcStr != null && !"null".equals(srcStr) && srcStr.length() != 0 && !"0".equals(srcStr) ? srcStr : "";
   }

   public static String nullToZeroString(String srcStr) {
      return srcStr != null && !"null".equals(srcStr) && srcStr.length() != 0 ? srcStr : "0";
   }

   public static String[] split(String source, String separator) throws NullPointerException {
      String[] returnVal = null;
      int cnt = 1;
      int index = source.indexOf(separator);

      int index0;
      for (index0 = 0; index >= 0; index = source.indexOf(separator, index + 1)) {
         ++cnt;
      }

      returnVal = new String[cnt];
      cnt = 0;

      for (index = source.indexOf(separator); index >= 0; ++cnt) {
         returnVal[cnt] = source.substring(index0, index);
         index0 = index + 1;
         index = source.indexOf(separator, index + 1);
      }

      returnVal[cnt] = source.substring(index0);
      return returnVal;
   }

   public static String cnvrEnterToSpace(String srcString) {
      String rtnStr = null;

      try {
         StringBuffer strTxt = new StringBuffer("");
         int len = srcString.length();

         for (int i = 0; i < len; ++i) {
            char chrBuff = srcString.charAt(i);
            switch (chrBuff) {
               case '\n' :
                  strTxt.append(" ");
                  break;
               case ' ' :
               case '\f' :
               default :
                  strTxt.append(chrBuff);
                  break;
               case '\r' :
                  strTxt.append(" ");
            }
         }

         rtnStr = strTxt.toString();
      } catch (Exception arg5) {
         ;
      }

      return rtnStr;
   }

   public static String getDigit(String s) {
      return s != null && !s.equals("") ? s.replaceAll("[^0-9.]*", "") : "";
   }

   public static String encodeMD5(String str) {
      StringBuffer sb = new StringBuffer();

      try {
         MessageDigest SHA-256 = MessageDigest.getInstance("SHA-256");
         SHA-256.update(str.getBytes());
         byte[] SHA-256Bytes = SHA-256.digest();

         for (int i = 0; i < SHA-256Bytes.length; ++i) {
            sb.append(SHA-256Bytes[i]);
         }
      } catch (Exception arg4) {
         ;
      }

      return sb.toString();
   }

   public static String getXml(Object obj) throws JAXBException, PropertyException {
      JAXBContext jc = JAXBContext.newInstance(new Class[]{obj.getClass()});
      Marshaller marshaller = jc.createMarshaller();
      marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "");
      marshaller.setProperty("jaxb.fragment", Boolean.valueOf(true));
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      StringWriter sw = new StringWriter();
      marshaller.marshal(obj, sw);
      return sw.toString();
   }
}
*/