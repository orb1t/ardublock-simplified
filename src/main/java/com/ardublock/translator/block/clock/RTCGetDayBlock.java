package com.ardublock.translator.block.clock;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;


public class RTCGetDayBlock extends TranslatorBlock
{
//	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
	
	public RTCGetDayBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{    
    
    // Generate variable
    String clockName = "rtcNow";
    String varName = translator.getNumberVariable(clockName);

		return codePrefix + varName + ".day()" + codeSuffix;
	}

}
