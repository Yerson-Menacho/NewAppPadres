package com.example.newapppadres.BD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.newapppadres.Entidades.Subtarea;
import com.example.newapppadres.Entidades.entTareas;
import com.example.newapppadres.R;

import java.util.ArrayList;
import java.util.List;

public class BdTareas extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tareas.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_TAREAS = "tareas";
    private static final String COLUMN_ID_TAREA = "id";
    private static final String COLUMN_NOMBRE_TAREA = "nombre";
    private static final String COLUMN_DETALLES_TAREA = "detalles";
    private static final String COLUMN_IMAGEN_TAREA = "imagen";
    private static final String COLUMN_PROGRESO_TAREA = "progreso";

    private static final String TABLE_SUBTAREAS = "subtareas";
    private static final String COLUMN_ID_SUBTAREA = "id";
    private static final String COLUMN_ID_TAREA_FK = "id_tarea";
    private static final String COLUMN_NOMBRE_SUBTAREA = "nombre";
    private static final String COLUMN_COMPLETADA_SUBTAREA = "completada";

    private static final String[] NOMBRES_TAREAS = {
            "Alimentación del Bebe", "Cambio de pañales", "Horas de sueño",
            "Baños y Cuidado de la Piel", "Visitas al Pediatra", "Tomas de Medicamentos",
            "Juegos y Estimulación", "Control de Vacunas", "Alerta de Crecimiento y Desarrollo"
    };

    private static final String[] DESCRIPCIONES_TAREAS = {
            "La alimentación es importante", "Cambia los pañales a su debido momento", "El siesta del bebe es importante",
            "su aseo debe ser cuidadoso", "Ir a pediatra", "Su saludo es importante",
            "Dejar que juegue pero bajo superbicion", "Llevar a su control", "Registras su crecieminto"
    };

    private static final int[] IMAGENES_TAREAS = {
            R.drawable.t, R.drawable.panal_bebe, R.drawable.suenio_bebe,
            R.drawable.banio_bebe, R.drawable.pediatra_bebe, R.drawable.medicina_bebe,
            R.drawable.jueg_bebe, R.drawable.vacuna_bebe, R.drawable.crecimiento_bebe
    };

    public BdTareas(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla tareas
        String queryTareas = "CREATE TABLE " + TABLE_TAREAS + " (" +
                COLUMN_ID_TAREA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE_TAREA + " TEXT, " +
                COLUMN_DETALLES_TAREA + " TEXT, " +
                COLUMN_IMAGEN_TAREA + " INTEGER, " +
                COLUMN_PROGRESO_TAREA + " INTEGER)";
        db.execSQL(queryTareas);

        // Crear la tabla subtareas
        String querySubtareas = "CREATE TABLE " + TABLE_SUBTAREAS + " (" +
                COLUMN_ID_SUBTAREA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID_TAREA_FK + " INTEGER, " +
                COLUMN_NOMBRE_SUBTAREA + " TEXT, " +
                COLUMN_COMPLETADA_SUBTAREA + " INTEGER DEFAULT 0, " + // 0 = false, 1 = true
                "FOREIGN KEY(" + COLUMN_ID_TAREA_FK + ") REFERENCES " + TABLE_TAREAS + "(" + COLUMN_ID_TAREA + ")" +
                ")";
        db.execSQL(querySubtareas);

        // Agregar las tareas predefinidas a la base de datos cuando se crea
        for (int i = 0; i < NOMBRES_TAREAS.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NOMBRE_TAREA, NOMBRES_TAREAS[i]);
            values.put(COLUMN_DETALLES_TAREA, DESCRIPCIONES_TAREAS[i]);
            values.put(COLUMN_IMAGEN_TAREA, IMAGENES_TAREAS[i]);
            values.put(COLUMN_PROGRESO_TAREA, 0);
            long idTarea = db.insert(TABLE_TAREAS, null, values);

            // Agregar las subtareas asociadas a cada tarea
            if (i == 0) { // Alimentación del Bebé
                insertSubtarea(db, idTarea, "Registrar horarios de lactancia o biberón");
                insertSubtarea(db, idTarea, "Registrar la cantidad de leche ingerida");
                insertSubtarea(db, idTarea, "Monitorear el cambio de pecho en la lactancia materna");
                insertSubtarea(db, idTarea, "Registrar la respuesta del bebé");
                insertSubtarea(db, idTarea, "Monitorear la introducción de alimentos sólidos");
            }

            if (i == 1) { // Cambio de pañales
                insertSubtarea(db, idTarea, "Registrar cada cambio de pañal");
                insertSubtarea(db, idTarea, "Anotar si el pañal estaba mojado o sucio");
                insertSubtarea(db, idTarea, "Monitorear la frecuencia de los cambios");
                insertSubtarea(db, idTarea, "Anotar la consistencia y el color de las heces");
                insertSubtarea(db, idTarea, "Registrar cualquier irritación o erupción en la piel");
            }

            if (i == 2) { // Horas de Sueño
                insertSubtarea(db, idTarea, "Registrar las siestas y el sueño nocturno");
                insertSubtarea(db, idTarea, "Anotar la duración de cada periodo de sueño");
                insertSubtarea(db, idTarea, "Monitorear patrones de sueño");
                insertSubtarea(db, idTarea, "Registrar despertares nocturnos");
                insertSubtarea(db, idTarea, "Anotar cualquier problema de sueño");
            }

            if (i == 3) { // Baños y Cuidado de la Piel
                insertSubtarea(db, idTarea, "Registrar cada baño del bebé");
                insertSubtarea(db, idTarea, "Anotar el uso de productos para la piel");
                insertSubtarea(db, idTarea, "Monitorear la frecuencia de los baños");
                insertSubtarea(db, idTarea, "Registrar la temperatura del agua");
                insertSubtarea(db, idTarea, "Anotar cualquier cambio en la piel");
            }

            if (i == 4) { // Visitas al Pediatra
                insertSubtarea(db, idTarea, "Registrar citas médicas");
                insertSubtarea(db, idTarea, "Anotar recomendaciones del pediatra");
                insertSubtarea(db, idTarea, "Monitorear el cumplimiento de las recomendaciones");
                insertSubtarea(db, idTarea, "Registrar resultados de exámenes");
                insertSubtarea(db, idTarea, "Anotar preguntas o preocupaciones para la próxima visita");
            }

            if (i == 5) { // Tomas de Medicamentos
                insertSubtarea(db, idTarea, "Registrar cada administración de medicamentos");
                insertSubtarea(db, idTarea, "Anotar dosis y horarios");
                insertSubtarea(db, idTarea, "Monitorear el cumplimiento del tratamiento");
                insertSubtarea(db, idTarea, "Registrar cualquier reacción o efecto secundario");
                insertSubtarea(db, idTarea, "Anotar la fecha de inicio y fin del tratamiento");
            }

            if (i == 6) { // Juegos y Estimulación
                insertSubtarea(db, idTarea, "Registrar actividades de juego y estimulación");
                insertSubtarea(db, idTarea, "Anotar la duración y tipo de actividad");
                insertSubtarea(db, idTarea, "Monitorear la variedad y frecuencia de las actividades");
                insertSubtarea(db, idTarea, "Registrar la respuesta del bebé");
                insertSubtarea(db, idTarea, "Anotar cualquier desarrollo observado durante el juego");
            }

            if (i == 7) { // Control de Vacunas
                insertSubtarea(db, idTarea, "Registrar cada vacuna recibida");
                insertSubtarea(db, idTarea, "Anotar fechas y posibles reacciones");
                insertSubtarea(db, idTarea, "Monitorear el calendario de vacunación");
                insertSubtarea(db, idTarea, "Anotar los refuerzos necesarios");
                insertSubtarea(db, idTarea, "Registrar el proveedor de la vacuna");
            }

            if (i == 8) { // Alerta de Crecimiento y Desarrollo
                insertSubtarea(db, idTarea, "Registrar peso y altura del bebé");
                insertSubtarea(db, idTarea, "Anotar hitos de desarrollo");
                insertSubtarea(db, idTarea, "Monitorear el progreso en el crecimiento y desarrollo");
                insertSubtarea(db, idTarea, "Registrar cualquier preocupación o retraso");
                insertSubtarea(db, idTarea, "Anotar consejos y actividades para fomentar el desarrollo");
            }

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBTAREAS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAREAS);
            onCreate(db);
        }
    }

    public void addTarea(entTareas tarea) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_TAREA, tarea.getNombreTarea());
        values.put(COLUMN_DETALLES_TAREA, tarea.getDetallesTarea());
        values.put(COLUMN_IMAGEN_TAREA, tarea.getImagen());
        values.put(COLUMN_PROGRESO_TAREA, tarea.getProgreso());
        db.insert(TABLE_TAREAS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<entTareas> getAllTareas() {
        ArrayList<entTareas> listaTareas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TAREAS;
        Cursor cursorTareas = db.rawQuery(query, null);

        if (cursorTareas.moveToFirst()) {
            do {
                int taskId = cursorTareas.getInt(cursorTareas.getColumnIndex(COLUMN_ID_TAREA));
                String nombreTarea = cursorTareas.getString(cursorTareas.getColumnIndex(COLUMN_NOMBRE_TAREA));
                String detallesTarea = cursorTareas.getString(cursorTareas.getColumnIndex(COLUMN_DETALLES_TAREA));
                int imagenTarea = cursorTareas.getInt(cursorTareas.getColumnIndex(COLUMN_IMAGEN_TAREA));
                int progresoTarea = cursorTareas.getInt(cursorTareas.getColumnIndex(COLUMN_PROGRESO_TAREA));

                // Obtener subtareas asociadas a la tarea actual
                List<Subtarea> subtareas = getSubtareasForTarea(db, taskId);

                // Crear instancia de entTareas con las subtareas
                entTareas tareaActual = new entTareas(taskId, nombreTarea, detallesTarea, imagenTarea, progresoTarea, (ArrayList<Subtarea>) subtareas);
                listaTareas.add(tareaActual);
            } while (cursorTareas.moveToNext());
        }

        cursorTareas.close();
        db.close();
        return listaTareas;
    }



    public void actualizarProgresoTarea(int id, int progreso) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROGRESO_TAREA, progreso);
        db.update(TABLE_TAREAS, values, COLUMN_ID_TAREA + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    private void insertSubtarea(SQLiteDatabase db, long idTarea, String nombreSubtarea) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_TAREA_FK, idTarea);
        values.put(COLUMN_NOMBRE_SUBTAREA, nombreSubtarea);
        db.insert(TABLE_SUBTAREAS, null, values);
    }

    @SuppressLint("Range")
    public static entTareas getTarea(Context context, int id) {
        BdTareas bdTareas = new BdTareas(context);
        SQLiteDatabase db = bdTareas.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TAREAS + " WHERE " + COLUMN_ID_TAREA + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        entTareas tarea = null;

        if (cursor.moveToFirst()) {
            int taskId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TAREA));
            String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_TAREA));
            String detalles = cursor.getString(cursor.getColumnIndex(COLUMN_DETALLES_TAREA));
            int imagen = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGEN_TAREA));
            int progreso = cursor.getInt(cursor.getColumnIndex(COLUMN_PROGRESO_TAREA));

            // Obtener las subtareas asociadas a la tarea
            List<Subtarea> subtareas = getSubtareasForTarea(db, taskId);

            tarea = new entTareas(taskId, nombre, detalles, imagen, progreso, subtareas);
        }

        cursor.close();
        db.close();
        bdTareas.close();
        return tarea;
    }


    @SuppressLint("Range")
    private static List<Subtarea> getSubtareasForTarea(SQLiteDatabase db, int tareaId) {
        List<Subtarea> subtareas = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_SUBTAREAS + " WHERE " + COLUMN_ID_TAREA_FK + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(tareaId)});

        if (cursor.moveToFirst()) {
            do {
                int subtareaId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_SUBTAREA));
                String nombreSubtarea = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_SUBTAREA));
                int completada = cursor.getInt(cursor.getColumnIndex(COLUMN_COMPLETADA_SUBTAREA));

                Subtarea subtarea = new Subtarea(subtareaId, nombreSubtarea, completada == 1);
                subtareas.add(subtarea);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return subtareas;
    }

    public void actualizarEstadoSubtarea(int idSubtarea, int completada) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPLETADA_SUBTAREA, completada);
        db.update(TABLE_SUBTAREAS, values, COLUMN_ID_SUBTAREA + " = ?", new String[]{String.valueOf(idSubtarea)});
        db.close();
    }

}
