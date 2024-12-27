from fastapi import Depends
from app.db.repository import update_file_status, update_file_status_with_data
import pandas as pd
from datetime import datetime
from database import SessionLocal, get_db

async def process_message(id: int, data: dict):
    try:
        db = get_db()
        update_file_status(db=db, train_id=id, status="In Progress")


        required_weather_cols = {
            "time": data["trainData"]["climate"]["daily"]["time"],
            "sunrise": data["trainData"]["climate"]["daily"]["sunrise"],
            "rain_sum": data["trainData"]["climate"]["daily"]["rain_sum"],
            "snowfall_sum": data["trainData"]["climate"]["daily"]["snowfall_sum"],
            "weather_code": data["trainData"]["climate"]["daily"]["weather_code"],
            "daylight_duration": data["trainData"]["climate"]["daily"]["daylight_duration"],
            "sunshine_duration": data["trainData"]["climate"]["daily"]["sunshine_duration"],
            "temperature_2m_max": data["trainData"]["climate"]["daily"]["temperature_2m_max"],
            "temperature_2m_min": data["trainData"]["climate"]["daily"]["temperature_2m_min"],
            "temperature_2m_mean": data["trainData"]["climate"]["daily"]["temperature_2m_mean"],
            "et0_fao_evapotranspiration": data["trainData"]["climate"]["daily"]["et0_fao_evapotranspiration"],
            "wind_direction_10m_dominant": data["trainData"]["climate"]["daily"]["wind_direction_10m_dominant"],
        }

        date_arranged_data = {}

        for indx in range(len(data["trainData"]["climate"]["daily"]["time"])):
            
            date = data["trainData"]["climate"]["daily"]["time"][indx]
            date_arranged_data[date] = {}
            for col in required_weather_cols:
                date_arranged_data[date][col] = data["trainData"]["climate"]["daily"][col][indx]



        species_data = data["trainData"]["biodiversity"]["results"]

        combined_cols = []

        for species in species_data:
            event_date = species["eventDate"]
            species_class = species["class"]

            species_genus = species["genus"]
            species_family = species["family"]
            species_order = species["order"]

            decimal_lat = species["decimalLatitude"]
            decimal_long = species["decimalLongitude"]

            scientificName = species["scientificName"]

            species_other_locations = species["gadm"]

            datetime_obj = datetime.fromisoformat(event_date)

            extracted_date = datetime_obj.strftime("%Y-%m-%d")

            weather_data = date_arranged_data[extracted_date]

            combined_cols.append({
                **weather_data,
                "species_class": species_class,
                "species_genus": species_genus,
                "species_family": species_family,
                "species_order": species_order,
                "scientific_name": scientificName,
                "decimal_latitude": decimal_lat,
                "decimal_longitude": decimal_long
                # "other_locations": species_other_locations,
            })

        df = pd.DataFrame(combined_cols)

        # df.to_csv(f"{data['id']}.csv", index=False)

        json_combined_cols = {"data": combined_cols}
        
        update_file_status_with_data(db=db, train_id=id, status="Completed", train_data = json_combined_cols)

    finally:
        db.close()
