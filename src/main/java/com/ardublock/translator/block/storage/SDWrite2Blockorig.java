package com.ardublock.translator.block.storage;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SDWrite2Blockorig extends TranslatorBlock
{
	public SDWrite2Blockorig(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
    SDWriteNumberBlock.setupSDHeader(translator);
    SDWriteNumberBlock.setupSDChipselect(translator);

		TranslatorBlock data = this.getRequiredTranslatorBlockAtSocket(0);
    String ret = "";
    String dfdecl = "";
    
		dfdecl = "\tFile datafile = SD.open(\""+data.toCode()+"\", FILE_WRITE);\n";
    translator.addDefinitionCommand(dfdecl);
    
		ret+="\tif(datafile){\n";
        data=this.getRequiredTranslatorBlockAtSocket(1, "\t\tdatafile.print( ", " );\n\t\tdatafile.print(\" \");\n");
        ret+=data.toCode();
        data=this.getRequiredTranslatorBlockAtSocket(2);
        String test=data.toCode();
		if(test.equals("true")){
		    ret+="\t\tdatafile.println(\"\");\n";
		}
		ret+="\t\tdatafile.close();\n";
        ret+="\t}\n";
		return  ret ;
	}
}
