package com.ardublock.translator.block.storage;

import com.ardublock.core.Context;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class TimestampSDWriteBlock extends TranslatorBlock
{
	public TimestampSDWriteBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
  
    // Add headers
    translator.addHeaderFile("Wire.h");
    translator.addHeaderFile("RTClib.h");
    translator.addHeaderFile("SPI.h");
    
    SDWriteNumberBlock.setupSDEnvironment(translator);    
  
		String ret = "";
		
		Context context = Context.getContext();
		if (context.getArduinoVersionString().equals(Context.ARDUINO_VERSION_UNKNOWN))
		{
			ret += "//Unable to detect your Arduino version, using 1.0 in default\n";
		}
		
		TranslatorBlock t1 = getRequiredTranslatorBlockAtSocket(0);
		String b1 = t1.toCode();
		TranslatorBlock t2 = getRequiredTranslatorBlockAtSocket(1);
		String b2 = t2.toCode();
		TranslatorBlock t3 = getRequiredTranslatorBlockAtSocket(2);
		String b3 = t3.toCode();
		//Switch was not used for compatibility with java 1.6

    // Generate variable
    String clockName = "rtcNow";
    String varName = translator.getNumberVariable(clockName);
  
    if (varName == null)
		{
      varName = translator.buildVariableName(clockName);
      translator.addNumberVariable(clockName, varName);
      translator.addDefinitionCommand("RTC_DS1307 RTC;");
      translator.addDefinitionCommand("DateTime " + varName + ";");
      translator.addSetupCommand("Wire.begin();");
		}
    // Add grab of time.
	  ret += "\n" + varName + " = RTC.now();\n";

		if (b2.equals("Return")) {

			ret += "__ardublockWriteStringSDln ( ";
			
        } else {

        	ret += "__ardublockWriteStringSD ( ";

		}		
    String timestamp = "";
      timestamp += "String(" + varName + ".year()" + ") + " + "\",\" + ";
      timestamp += "String(" +  varName + ".month()" + ") + " + "\",\" + ";
      timestamp += "String(" +  varName + ".day()" + ") + " + "\",\" + ";
      timestamp += "String(" +  varName + ".hour()" + ") + " + "\",\" + ";
      timestamp += "String(" +  varName + ".minute()" + ") + " + "\",\" + ";
    
      ret = ret + "\"" + b1 + "\"";
      ret = ret +",";
      ret = ret + timestamp + b2;
      ret = ret + ");\n";


      
		return codePrefix + ret + codeSuffix;
	}
	


	
}
