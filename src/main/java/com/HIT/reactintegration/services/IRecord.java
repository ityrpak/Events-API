package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.RecordDTO;
import com.HIT.reactintegration.dtos.RecordWDateDTO;

import java.util.HashMap;
import java.util.Map;

public interface IRecord {
    Map createRecord(RecordDTO recordContent);

    Map getAllRecords();
}
