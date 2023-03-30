import xmltodict, json

def convert_xml_to_json(timetable_xml: str):
    # convert xml to dict
    data_dict = xmltodict.parse(timetable_xml)

    # convert dict to json
    return json.dumps(data_dict, ensure_ascii = False, indent = 4)