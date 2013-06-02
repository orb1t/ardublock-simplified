package com.ardublock.translator.block.clock;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RTCGetYearBlock extends TranslatorBlock
{
//	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
	
	public RTCGetYearBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{    
    
    // Generate variable
    String clockName = "rtcNow";
    String varName = translator.getNumberVariable(clockName);
    /***
    if (varName == null)
		{
      // Add headers
      translator.addHeaderFile("Wire.h");
      translator.addHeaderFile("RTClib.h");
      
      varName = translator.buildVariableName(clockName);
      translator.addNumberVariable(clockName, varName);
      translator.addDefinitionCommand("RTC_DS1307 RTC;");
      translator.addDefinitionCommand("DateTime " + varName + ";");
    }
      ***/
    
		return codePrefix + varName + ".year()" + codeSuffix;
	}
}
