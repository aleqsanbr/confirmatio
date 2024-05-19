package com.aleqsanbr.confirmatio

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import kotlin.random.Random

/**
 * Implementation of App Widget functionality.
 */
private const val WIDGET_UPDATE = "update"
private const val WIDGET_TEXT_ID = "widget_text_id"
class QuoteWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {

        if (WIDGET_UPDATE == intent.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val views = RemoteViews(context.packageName, R.layout.quote_widget)
            val lastUpdate = getLine(context)
            val appWidgetId = intent.getIntExtra(WIDGET_TEXT_ID, 0)
            val remoteViews = RemoteViews(context.getPackageName(), R.layout.quote_widget).also {
                it.setTextViewText(R.id.widget_text2, "Updated text")
            }
            remoteViews.setTextViewText(R.id.widget_text2, lastUpdate)
            appWidgetManager.partiallyUpdateAppWidget(appWidgetId, remoteViews)
        }
        super.onReceive(context, intent)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.quote_widget)

    views.setTextViewText(R.id.widget_text2, getLine(context))

    val mainIntent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 0, mainIntent,
        PendingIntent.FLAG_IMMUTABLE)

    views.setOnClickPendingIntent(R.id.logo_button, pendingIntent)

    views.setOnClickPendingIntent(R.id.update_button, getPendingSelfIntent(context, appWidgetId,
        WIDGET_UPDATE))


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

private fun getPendingSelfIntent(context: Context, appWidgetId: Int, action: String): PendingIntent {
    val intent = Intent(context, QuoteWidget::class.java)
    intent.action = action
    intent.putExtra(WIDGET_TEXT_ID, appWidgetId)
    return PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_IMMUTABLE)
}

fun getLine(context : Context) : String {
    val reader = context.assets.open("widget_phrases.txt").bufferedReader()
    var content = emptyList<String>()
    try {
        var line = reader.readLine()
        while (line != null) {
            content = content.plus(line)
            line = reader.readLine()
        }
    } finally {
        reader.close()
    }
    return  content.get(Math.abs(Random.nextInt()) % content.size)
}