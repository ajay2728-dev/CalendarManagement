/**
 * Autogenerated by Thrift Compiler (0.21.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.example.thriftMeeting;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.21.0)", date = "2025-02-20")
public class IMeetingServiceDTO implements org.apache.thrift.TBase<IMeetingServiceDTO, IMeetingServiceDTO._Fields>, java.io.Serializable, Cloneable, Comparable<IMeetingServiceDTO> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("IMeetingServiceDTO");

  private static final org.apache.thrift.protocol.TField MEETING_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("meetingId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField AGENDA_FIELD_DESC = new org.apache.thrift.protocol.TField("agenda", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField EMPLOYEE_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("employeeIDs", org.apache.thrift.protocol.TType.LIST, (short)4);
  private static final org.apache.thrift.protocol.TField START_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("startTime", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField END_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("endTime", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField ROOM_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("roomId", org.apache.thrift.protocol.TType.I32, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new IMeetingServiceDTOStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new IMeetingServiceDTOTupleSchemeFactory();

  public int meetingId; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String description; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String agenda; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.lang.Integer> employeeIDs; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String startTime; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String endTime; // required
  public int roomId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MEETING_ID((short)1, "meetingId"),
    DESCRIPTION((short)2, "description"),
    AGENDA((short)3, "agenda"),
    EMPLOYEE_IDS((short)4, "employeeIDs"),
    START_TIME((short)5, "startTime"),
    END_TIME((short)6, "endTime"),
    ROOM_ID((short)7, "roomId");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // MEETING_ID
          return MEETING_ID;
        case 2: // DESCRIPTION
          return DESCRIPTION;
        case 3: // AGENDA
          return AGENDA;
        case 4: // EMPLOYEE_IDS
          return EMPLOYEE_IDS;
        case 5: // START_TIME
          return START_TIME;
        case 6: // END_TIME
          return END_TIME;
        case 7: // ROOM_ID
          return ROOM_ID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    @Override
    public short getThriftFieldId() {
      return _thriftId;
    }

    @Override
    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __MEETINGID_ISSET_ID = 0;
  private static final int __ROOMID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MEETING_ID, new org.apache.thrift.meta_data.FieldMetaData("meetingId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AGENDA, new org.apache.thrift.meta_data.FieldMetaData("agenda", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EMPLOYEE_IDS, new org.apache.thrift.meta_data.FieldMetaData("employeeIDs", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.START_TIME, new org.apache.thrift.meta_data.FieldMetaData("startTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.END_TIME, new org.apache.thrift.meta_data.FieldMetaData("endTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROOM_ID, new org.apache.thrift.meta_data.FieldMetaData("roomId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(IMeetingServiceDTO.class, metaDataMap);
  }

  public IMeetingServiceDTO() {
  }

  public IMeetingServiceDTO(
    int meetingId,
    java.lang.String description,
    java.lang.String agenda,
    java.util.List<java.lang.Integer> employeeIDs,
    java.lang.String startTime,
    java.lang.String endTime,
    int roomId)
  {
    this();
    this.meetingId = meetingId;
    setMeetingIdIsSet(true);
    this.description = description;
    this.agenda = agenda;
    this.employeeIDs = employeeIDs;
    this.startTime = startTime;
    this.endTime = endTime;
    this.roomId = roomId;
    setRoomIdIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public IMeetingServiceDTO(IMeetingServiceDTO other) {
    __isset_bitfield = other.__isset_bitfield;
    this.meetingId = other.meetingId;
    if (other.isSetDescription()) {
      this.description = other.description;
    }
    if (other.isSetAgenda()) {
      this.agenda = other.agenda;
    }
    if (other.isSetEmployeeIDs()) {
      java.util.List<java.lang.Integer> __this__employeeIDs = new java.util.ArrayList<java.lang.Integer>(other.employeeIDs);
      this.employeeIDs = __this__employeeIDs;
    }
    if (other.isSetStartTime()) {
      this.startTime = other.startTime;
    }
    if (other.isSetEndTime()) {
      this.endTime = other.endTime;
    }
    this.roomId = other.roomId;
  }

  @Override
  public IMeetingServiceDTO deepCopy() {
    return new IMeetingServiceDTO(this);
  }

  @Override
  public void clear() {
    setMeetingIdIsSet(false);
    this.meetingId = 0;
    this.description = null;
    this.agenda = null;
    this.employeeIDs = null;
    this.startTime = null;
    this.endTime = null;
    setRoomIdIsSet(false);
    this.roomId = 0;
  }

  public int getMeetingId() {
    return this.meetingId;
  }

  public IMeetingServiceDTO setMeetingId(int meetingId) {
    this.meetingId = meetingId;
    setMeetingIdIsSet(true);
    return this;
  }

  public void unsetMeetingId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MEETINGID_ISSET_ID);
  }

  /** Returns true if field meetingId is set (has been assigned a value) and false otherwise */
  public boolean isSetMeetingId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MEETINGID_ISSET_ID);
  }

  public void setMeetingIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MEETINGID_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getDescription() {
    return this.description;
  }

  public IMeetingServiceDTO setDescription(@org.apache.thrift.annotation.Nullable java.lang.String description) {
    this.description = description;
    return this;
  }

  public void unsetDescription() {
    this.description = null;
  }

  /** Returns true if field description is set (has been assigned a value) and false otherwise */
  public boolean isSetDescription() {
    return this.description != null;
  }

  public void setDescriptionIsSet(boolean value) {
    if (!value) {
      this.description = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getAgenda() {
    return this.agenda;
  }

  public IMeetingServiceDTO setAgenda(@org.apache.thrift.annotation.Nullable java.lang.String agenda) {
    this.agenda = agenda;
    return this;
  }

  public void unsetAgenda() {
    this.agenda = null;
  }

  /** Returns true if field agenda is set (has been assigned a value) and false otherwise */
  public boolean isSetAgenda() {
    return this.agenda != null;
  }

  public void setAgendaIsSet(boolean value) {
    if (!value) {
      this.agenda = null;
    }
  }

  public int getEmployeeIDsSize() {
    return (this.employeeIDs == null) ? 0 : this.employeeIDs.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.Integer> getEmployeeIDsIterator() {
    return (this.employeeIDs == null) ? null : this.employeeIDs.iterator();
  }

  public void addToEmployeeIDs(int elem) {
    if (this.employeeIDs == null) {
      this.employeeIDs = new java.util.ArrayList<java.lang.Integer>();
    }
    this.employeeIDs.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.lang.Integer> getEmployeeIDs() {
    return this.employeeIDs;
  }

  public IMeetingServiceDTO setEmployeeIDs(@org.apache.thrift.annotation.Nullable java.util.List<java.lang.Integer> employeeIDs) {
    this.employeeIDs = employeeIDs;
    return this;
  }

  public void unsetEmployeeIDs() {
    this.employeeIDs = null;
  }

  /** Returns true if field employeeIDs is set (has been assigned a value) and false otherwise */
  public boolean isSetEmployeeIDs() {
    return this.employeeIDs != null;
  }

  public void setEmployeeIDsIsSet(boolean value) {
    if (!value) {
      this.employeeIDs = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getStartTime() {
    return this.startTime;
  }

  public IMeetingServiceDTO setStartTime(@org.apache.thrift.annotation.Nullable java.lang.String startTime) {
    this.startTime = startTime;
    return this;
  }

  public void unsetStartTime() {
    this.startTime = null;
  }

  /** Returns true if field startTime is set (has been assigned a value) and false otherwise */
  public boolean isSetStartTime() {
    return this.startTime != null;
  }

  public void setStartTimeIsSet(boolean value) {
    if (!value) {
      this.startTime = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getEndTime() {
    return this.endTime;
  }

  public IMeetingServiceDTO setEndTime(@org.apache.thrift.annotation.Nullable java.lang.String endTime) {
    this.endTime = endTime;
    return this;
  }

  public void unsetEndTime() {
    this.endTime = null;
  }

  /** Returns true if field endTime is set (has been assigned a value) and false otherwise */
  public boolean isSetEndTime() {
    return this.endTime != null;
  }

  public void setEndTimeIsSet(boolean value) {
    if (!value) {
      this.endTime = null;
    }
  }

  public int getRoomId() {
    return this.roomId;
  }

  public IMeetingServiceDTO setRoomId(int roomId) {
    this.roomId = roomId;
    setRoomIdIsSet(true);
    return this;
  }

  public void unsetRoomId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ROOMID_ISSET_ID);
  }

  /** Returns true if field roomId is set (has been assigned a value) and false otherwise */
  public boolean isSetRoomId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ROOMID_ISSET_ID);
  }

  public void setRoomIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ROOMID_ISSET_ID, value);
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case MEETING_ID:
      if (value == null) {
        unsetMeetingId();
      } else {
        setMeetingId((java.lang.Integer)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((java.lang.String)value);
      }
      break;

    case AGENDA:
      if (value == null) {
        unsetAgenda();
      } else {
        setAgenda((java.lang.String)value);
      }
      break;

    case EMPLOYEE_IDS:
      if (value == null) {
        unsetEmployeeIDs();
      } else {
        setEmployeeIDs((java.util.List<java.lang.Integer>)value);
      }
      break;

    case START_TIME:
      if (value == null) {
        unsetStartTime();
      } else {
        setStartTime((java.lang.String)value);
      }
      break;

    case END_TIME:
      if (value == null) {
        unsetEndTime();
      } else {
        setEndTime((java.lang.String)value);
      }
      break;

    case ROOM_ID:
      if (value == null) {
        unsetRoomId();
      } else {
        setRoomId((java.lang.Integer)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case MEETING_ID:
      return getMeetingId();

    case DESCRIPTION:
      return getDescription();

    case AGENDA:
      return getAgenda();

    case EMPLOYEE_IDS:
      return getEmployeeIDs();

    case START_TIME:
      return getStartTime();

    case END_TIME:
      return getEndTime();

    case ROOM_ID:
      return getRoomId();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  @Override
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case MEETING_ID:
      return isSetMeetingId();
    case DESCRIPTION:
      return isSetDescription();
    case AGENDA:
      return isSetAgenda();
    case EMPLOYEE_IDS:
      return isSetEmployeeIDs();
    case START_TIME:
      return isSetStartTime();
    case END_TIME:
      return isSetEndTime();
    case ROOM_ID:
      return isSetRoomId();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof IMeetingServiceDTO)
      return this.equals((IMeetingServiceDTO)that);
    return false;
  }

  public boolean equals(IMeetingServiceDTO that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_meetingId = true;
    boolean that_present_meetingId = true;
    if (this_present_meetingId || that_present_meetingId) {
      if (!(this_present_meetingId && that_present_meetingId))
        return false;
      if (this.meetingId != that.meetingId)
        return false;
    }

    boolean this_present_description = true && this.isSetDescription();
    boolean that_present_description = true && that.isSetDescription();
    if (this_present_description || that_present_description) {
      if (!(this_present_description && that_present_description))
        return false;
      if (!this.description.equals(that.description))
        return false;
    }

    boolean this_present_agenda = true && this.isSetAgenda();
    boolean that_present_agenda = true && that.isSetAgenda();
    if (this_present_agenda || that_present_agenda) {
      if (!(this_present_agenda && that_present_agenda))
        return false;
      if (!this.agenda.equals(that.agenda))
        return false;
    }

    boolean this_present_employeeIDs = true && this.isSetEmployeeIDs();
    boolean that_present_employeeIDs = true && that.isSetEmployeeIDs();
    if (this_present_employeeIDs || that_present_employeeIDs) {
      if (!(this_present_employeeIDs && that_present_employeeIDs))
        return false;
      if (!this.employeeIDs.equals(that.employeeIDs))
        return false;
    }

    boolean this_present_startTime = true && this.isSetStartTime();
    boolean that_present_startTime = true && that.isSetStartTime();
    if (this_present_startTime || that_present_startTime) {
      if (!(this_present_startTime && that_present_startTime))
        return false;
      if (!this.startTime.equals(that.startTime))
        return false;
    }

    boolean this_present_endTime = true && this.isSetEndTime();
    boolean that_present_endTime = true && that.isSetEndTime();
    if (this_present_endTime || that_present_endTime) {
      if (!(this_present_endTime && that_present_endTime))
        return false;
      if (!this.endTime.equals(that.endTime))
        return false;
    }

    boolean this_present_roomId = true;
    boolean that_present_roomId = true;
    if (this_present_roomId || that_present_roomId) {
      if (!(this_present_roomId && that_present_roomId))
        return false;
      if (this.roomId != that.roomId)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + meetingId;

    hashCode = hashCode * 8191 + ((isSetDescription()) ? 131071 : 524287);
    if (isSetDescription())
      hashCode = hashCode * 8191 + description.hashCode();

    hashCode = hashCode * 8191 + ((isSetAgenda()) ? 131071 : 524287);
    if (isSetAgenda())
      hashCode = hashCode * 8191 + agenda.hashCode();

    hashCode = hashCode * 8191 + ((isSetEmployeeIDs()) ? 131071 : 524287);
    if (isSetEmployeeIDs())
      hashCode = hashCode * 8191 + employeeIDs.hashCode();

    hashCode = hashCode * 8191 + ((isSetStartTime()) ? 131071 : 524287);
    if (isSetStartTime())
      hashCode = hashCode * 8191 + startTime.hashCode();

    hashCode = hashCode * 8191 + ((isSetEndTime()) ? 131071 : 524287);
    if (isSetEndTime())
      hashCode = hashCode * 8191 + endTime.hashCode();

    hashCode = hashCode * 8191 + roomId;

    return hashCode;
  }

  @Override
  public int compareTo(IMeetingServiceDTO other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetMeetingId(), other.isSetMeetingId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMeetingId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.meetingId, other.meetingId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetDescription(), other.isSetDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.description, other.description);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetAgenda(), other.isSetAgenda());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgenda()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.agenda, other.agenda);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetEmployeeIDs(), other.isSetEmployeeIDs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmployeeIDs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.employeeIDs, other.employeeIDs);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetStartTime(), other.isSetStartTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startTime, other.startTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetEndTime(), other.isSetEndTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEndTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.endTime, other.endTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetRoomId(), other.isSetRoomId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoomId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roomId, other.roomId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  @Override
  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  @Override
  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("IMeetingServiceDTO(");
    boolean first = true;

    sb.append("meetingId:");
    sb.append(this.meetingId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("description:");
    if (this.description == null) {
      sb.append("null");
    } else {
      sb.append(this.description);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("agenda:");
    if (this.agenda == null) {
      sb.append("null");
    } else {
      sb.append(this.agenda);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("employeeIDs:");
    if (this.employeeIDs == null) {
      sb.append("null");
    } else {
      sb.append(this.employeeIDs);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("startTime:");
    if (this.startTime == null) {
      sb.append("null");
    } else {
      sb.append(this.startTime);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("endTime:");
    if (this.endTime == null) {
      sb.append("null");
    } else {
      sb.append(this.endTime);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("roomId:");
    sb.append(this.roomId);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class IMeetingServiceDTOStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public IMeetingServiceDTOStandardScheme getScheme() {
      return new IMeetingServiceDTOStandardScheme();
    }
  }

  private static class IMeetingServiceDTOStandardScheme extends org.apache.thrift.scheme.StandardScheme<IMeetingServiceDTO> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, IMeetingServiceDTO struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MEETING_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.meetingId = iprot.readI32();
              struct.setMeetingIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.description = iprot.readString();
              struct.setDescriptionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // AGENDA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.agenda = iprot.readString();
              struct.setAgendaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // EMPLOYEE_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.employeeIDs = new java.util.ArrayList<java.lang.Integer>(_list0.size);
                int _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readI32();
                  struct.employeeIDs.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setEmployeeIDsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // START_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.startTime = iprot.readString();
              struct.setStartTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // END_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.endTime = iprot.readString();
              struct.setEndTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // ROOM_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.roomId = iprot.readI32();
              struct.setRoomIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    @Override
    public void write(org.apache.thrift.protocol.TProtocol oprot, IMeetingServiceDTO struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(MEETING_ID_FIELD_DESC);
      oprot.writeI32(struct.meetingId);
      oprot.writeFieldEnd();
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      if (struct.agenda != null) {
        oprot.writeFieldBegin(AGENDA_FIELD_DESC);
        oprot.writeString(struct.agenda);
        oprot.writeFieldEnd();
      }
      if (struct.employeeIDs != null) {
        oprot.writeFieldBegin(EMPLOYEE_IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, struct.employeeIDs.size()));
          for (int _iter3 : struct.employeeIDs)
          {
            oprot.writeI32(_iter3);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.startTime != null) {
        oprot.writeFieldBegin(START_TIME_FIELD_DESC);
        oprot.writeString(struct.startTime);
        oprot.writeFieldEnd();
      }
      if (struct.endTime != null) {
        oprot.writeFieldBegin(END_TIME_FIELD_DESC);
        oprot.writeString(struct.endTime);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ROOM_ID_FIELD_DESC);
      oprot.writeI32(struct.roomId);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class IMeetingServiceDTOTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public IMeetingServiceDTOTupleScheme getScheme() {
      return new IMeetingServiceDTOTupleScheme();
    }
  }

  private static class IMeetingServiceDTOTupleScheme extends org.apache.thrift.scheme.TupleScheme<IMeetingServiceDTO> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, IMeetingServiceDTO struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetMeetingId()) {
        optionals.set(0);
      }
      if (struct.isSetDescription()) {
        optionals.set(1);
      }
      if (struct.isSetAgenda()) {
        optionals.set(2);
      }
      if (struct.isSetEmployeeIDs()) {
        optionals.set(3);
      }
      if (struct.isSetStartTime()) {
        optionals.set(4);
      }
      if (struct.isSetEndTime()) {
        optionals.set(5);
      }
      if (struct.isSetRoomId()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetMeetingId()) {
        oprot.writeI32(struct.meetingId);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
      if (struct.isSetAgenda()) {
        oprot.writeString(struct.agenda);
      }
      if (struct.isSetEmployeeIDs()) {
        {
          oprot.writeI32(struct.employeeIDs.size());
          for (int _iter4 : struct.employeeIDs)
          {
            oprot.writeI32(_iter4);
          }
        }
      }
      if (struct.isSetStartTime()) {
        oprot.writeString(struct.startTime);
      }
      if (struct.isSetEndTime()) {
        oprot.writeString(struct.endTime);
      }
      if (struct.isSetRoomId()) {
        oprot.writeI32(struct.roomId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, IMeetingServiceDTO struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.meetingId = iprot.readI32();
        struct.setMeetingIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
      if (incoming.get(2)) {
        struct.agenda = iprot.readString();
        struct.setAgendaIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TList _list5 = iprot.readListBegin(org.apache.thrift.protocol.TType.I32);
          struct.employeeIDs = new java.util.ArrayList<java.lang.Integer>(_list5.size);
          int _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = iprot.readI32();
            struct.employeeIDs.add(_elem6);
          }
        }
        struct.setEmployeeIDsIsSet(true);
      }
      if (incoming.get(4)) {
        struct.startTime = iprot.readString();
        struct.setStartTimeIsSet(true);
      }
      if (incoming.get(5)) {
        struct.endTime = iprot.readString();
        struct.setEndTimeIsSet(true);
      }
      if (incoming.get(6)) {
        struct.roomId = iprot.readI32();
        struct.setRoomIdIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

