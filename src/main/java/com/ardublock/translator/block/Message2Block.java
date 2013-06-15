package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Message2Block extends TranslatorBlock
{
	public Message2Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
    // System.out.println("MessageBlock2");
    
		//TODO take out special character
		String ret;
		ret = label.replaceAll("\\\\", "\\\\\\\\");
		ret = ret.replaceAll("\"", "\\\\\"");
    // 20150226 MCJ Added a plus to the output... may not work in all situations?
		ret = codePrefix + "\"" + ret + "\"" ;
    ret += codeSuffix;
    
		TranslatorBlock translatorBlock = this.getTranslatorBlockAtSocket(0, codePrefix, codeSuffix);
		if (translatorBlock != null)
		{
			ret = ret + " + " + translatorBlock.toCode();
		}
		return ret;
	}

}
