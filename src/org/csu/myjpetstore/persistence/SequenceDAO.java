package org.csu.myjpetstore.persistence;

import org.csu.myjpetstore.domain.Sequence;

public interface SequenceDAO {
	Sequence getSequence(Sequence sequence);
	String getSequenceString = "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
	
	void updateSequence(Sequence sequence);
	String updateSequenceString = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";
}
