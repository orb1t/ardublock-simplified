package com.ardublock.translator.block.clock;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;


public class RTCInitBlock extends TranslatorBlock
{
//	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
	
	public RTCInitBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{    
    // Add headers
    translator.addHeaderFile("Wire.h");
    translator.addHeaderFile("RTClib.h");
    
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
		
    return codePrefix + varName + " = RTC.now();\n" + codeSuffix;
	}
}
